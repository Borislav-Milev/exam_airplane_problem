package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.root.TicketSeedRootDto;
import softuni.exam.models.dto.seed.TicketSeedDto;
import softuni.exam.models.entity.Passenger;
import softuni.exam.models.entity.Plane;
import softuni.exam.models.entity.Ticket;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.TicketRepository;
import softuni.exam.service.contracts.PassengerService;
import softuni.exam.service.contracts.PlaneService;
import softuni.exam.service.contracts.TicketService;
import softuni.exam.service.contracts.TownService;
import softuni.exam.util.contract.FileReader;
import softuni.exam.util.contract.ValidationUtil;
import softuni.exam.util.contract.XmlParser;

import static softuni.exam.constant.FilePath.TICKETS_FILE_PATH;
import static softuni.exam.constant.Message.IMPORTED_TICKET;
import static softuni.exam.constant.Message.INVALID_TICKET;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final PlaneService planeService;
    private final PassengerService passengerService;
    private final TownService townService;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final FileReader fileReader;
    private final StringBuilder output;

    public TicketServiceImpl(TicketRepository ticketRepository, PlaneService planeService,
                             PassengerService passengerService, TownService townService, ModelMapper modelMapper,
                             XmlParser xmlParser, ValidationUtil validationUtil, FileReader fileReader,
                             StringBuilder output) {
        this.ticketRepository = ticketRepository;
        this.planeService = planeService;
        this.passengerService = passengerService;
        this.townService = townService;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.fileReader = fileReader;
        this.output = output;
    }

    @Override
    public boolean areImported() {
        return this.ticketRepository.count() != 0;
    }

    @Override
    public String readTicketsFileContent() {
        return this.fileReader.readFile(TICKETS_FILE_PATH);
    }

    @Override
    public String importTickets() {
        TicketSeedRootDto ticketSeedRootDto = this.xmlParser.parse(TicketSeedRootDto.class, TICKETS_FILE_PATH);
        for (TicketSeedDto ticketSeedDto : ticketSeedRootDto.getTicketSeedDtos()) {
            Plane plane = this.planeService.getPlaneBySerialNumber(ticketSeedDto.getPlaneDto().getSerialNumber());
            Town fromTown = this.townService.getTownByName(ticketSeedDto.getFromTown().getName());
            Town toTown = this.townService.getTownByName(ticketSeedDto.getToTown().getName());
            Passenger passenger = this.passengerService.getPassengerByEmail(ticketSeedDto.getPassengerDto().getEmail());
            if (null == plane || null == fromTown || null == toTown || null == passenger ||
                    !validationUtil.isValid(ticketSeedDto)) {
                this.output.append(INVALID_TICKET).append(System.lineSeparator());
                continue;
            }
            Ticket ticket = this.modelMapper.map(ticketSeedDto, Ticket.class);
            ticket.setPassenger(passenger);
            ticket.setPlane(plane);
            ticket.setFromTown(fromTown);
            ticket.setToTown(toTown);
            try {
                this.ticketRepository.saveAndFlush(ticket);
            }catch (Exception e){
                this.output.append(INVALID_TICKET).append(System.lineSeparator());
                continue;
            }
            this.output.append(String.format(IMPORTED_TICKET, fromTown.getName(), toTown.getName()))
                    .append(System.lineSeparator());
        }
        return this.output.toString();
    }
}
