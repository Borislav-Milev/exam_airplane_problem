package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.root.PlaneSeedRootDto;
import softuni.exam.models.dto.seed.PlaneSeedDto;
import softuni.exam.models.entity.Plane;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.service.contracts.PlaneService;
import softuni.exam.util.contract.FileReader;
import softuni.exam.util.contract.ValidationUtil;
import softuni.exam.util.contract.XmlParser;

import static softuni.exam.constant.FilePath.PLANES_FILE_PATH;
import static softuni.exam.constant.Message.IMPORTED_PLANE;
import static softuni.exam.constant.Message.INVALID_PLANE;

@Service
public class PlaneServiceImpl implements PlaneService {

    private final PlaneRepository planeRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final FileReader fileReader;
    private final StringBuilder output;

    public PlaneServiceImpl(PlaneRepository planeRepository, ModelMapper modelMapper, XmlParser xmlParser,
                            ValidationUtil validationUtil, FileReader fileReader, StringBuilder output) {
        this.planeRepository = planeRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.fileReader = fileReader;
        this.output = output;
    }

    @Override
    public boolean areImported() {
        return this.planeRepository.count() != 0;
    }

    @Override
    public String readPlanesFileContent() {
        return fileReader.readFile(PLANES_FILE_PATH);
    }

    @Override
    public String importPlanes() {
        PlaneSeedRootDto planeSeedRootDto = this.xmlParser.parse(PlaneSeedRootDto.class, PLANES_FILE_PATH);
        for (PlaneSeedDto planeSeedDto : planeSeedRootDto.getPlaneSeedDtos()) {
            if(!this.validationUtil.isValid(planeSeedDto)){
                this.output.append(INVALID_PLANE).append(System.lineSeparator());
                continue;
            }
            this.planeRepository.saveAndFlush(this.modelMapper.map(planeSeedDto, Plane.class));
            this.output.append(String.format(IMPORTED_PLANE, planeSeedDto.getSerialNumber()))
                    .append(System.lineSeparator());
        }
        return this.output.toString();
    }
    @Override
    public Plane getPlaneBySerialNumber(String serialNumber){
        return this.planeRepository.getPlaneBySerialNumber(serialNumber).orElse(null);
    }
}
