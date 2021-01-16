package softuni.exam.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.seed.PassengerSeedDto;
import softuni.exam.models.entity.Passenger;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.service.contracts.PassengerService;
import softuni.exam.service.contracts.TownService;
import softuni.exam.util.contract.FileReader;
import softuni.exam.util.contract.ValidationUtil;

import java.io.IOException;

import static softuni.exam.constant.FilePath.PASSENGERS_FILE_PATH;
import static softuni.exam.constant.Message.IMPORTED_PASSENGER;
import static softuni.exam.constant.Message.INVALID_PASSENGER;

@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final ValidationUtil validationUtil;
    private final TownService townService;
    private final ModelMapper modelMapper;
    private final FileReader fileReader;
    private final StringBuilder output;
    private final Gson gson;

    public PassengerServiceImpl(PassengerRepository passengerRepository, TownService townService, ValidationUtil validationUtil,
                                FileReader fileReader, ModelMapper modelMapper, Gson gson, StringBuilder output) {
        this.passengerRepository = passengerRepository;
        this.townService = townService;
        this.validationUtil = validationUtil;
        this.fileReader = fileReader;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.output = output;
    }

    @Override
    public boolean areImported() {
        return this.passengerRepository.count() != 0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        return this.fileReader.readFile(PASSENGERS_FILE_PATH);
    }

    @Override
    public String importPassengers() throws IOException {
        PassengerSeedDto[] passengerDtos = this.gson
                .fromJson(this.readPassengersFileContent(), PassengerSeedDto[].class);

        for (PassengerSeedDto passengerDto : passengerDtos) {
            Town town = this.townService.getTownByName(passengerDto.getTown());
            if(null == town || !this.validationUtil.isValid(passengerDto)){
                this.output.append(INVALID_PASSENGER).append(System.lineSeparator());
                continue;
            }
            Passenger passenger = this.modelMapper.map(passengerDto, Passenger.class);
            passenger.setTown(town);
            this.passengerRepository.saveAndFlush(passenger);
            this.output.append(String.format(IMPORTED_PASSENGER, passenger.getLastName(), passenger.getEmail()))
                    .append(System.lineSeparator());
        }
        return this.output.toString();
    }

    @Override
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {
        this.output.setLength(0);
        this.passengerRepository.getPassengerByTicketsAndEmail().forEach(passenger -> {
            this.output.append(String.format("Passenger %s %s", passenger.getFirstName(), passenger.getLastName()))
                    .append(System.lineSeparator())
                    .append("\tEmail - ").append(passenger.getEmail())
                    .append(System.lineSeparator())
                    .append("\tPhone - ").append(passenger.getPhoneNumber())
                    .append(System.lineSeparator())
                    .append("\tNumber of tickets - ").append(passenger.getTickets().size())
                    .append(System.lineSeparator())
                    .append(System.lineSeparator());
        });
        return this.output.toString();
    }
    @Override
    public Passenger getPassengerByEmail(String email){
        return this.passengerRepository.getPassengerByEmail(email).orElse(null);
    }
}
