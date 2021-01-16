package softuni.exam.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.seed.TownSeedDto;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.contracts.TownService;
import softuni.exam.util.contract.FileReader;
import softuni.exam.util.contract.ValidationUtil;

import static softuni.exam.constant.FilePath.TOWNS_FILE_PATH;
import static softuni.exam.constant.Message.INVALID_TOWN;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final FileReader fileReader;
    private final StringBuilder output;
    private final Gson gson;

    public TownServiceImpl(TownRepository townRepository, ValidationUtil validationUtil, FileReader fileReader,
                           ModelMapper modelMapper, StringBuilder output, Gson gson) {
        this.townRepository = townRepository;
        this.validationUtil = validationUtil;
        this.fileReader = fileReader;
        this.modelMapper = modelMapper;
        this.output = output;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() != 0;
    }

    @Override
    public String readTownsFileContent() {
        return this.fileReader.readFile(TOWNS_FILE_PATH);
    }

    @Override
    public String importTowns() {
        TownSeedDto[] townSeedDtos = this.gson.fromJson(this.readTownsFileContent(), TownSeedDto[].class);
        for (TownSeedDto townSeedDto : townSeedDtos) {
            if (!validationUtil.isValid(townSeedDto)) {
                this.output.append(INVALID_TOWN).append(System.lineSeparator());
                continue;
            }
            this.townRepository.saveAndFlush(this.modelMapper.map(townSeedDto, Town.class));
            this.output.append(String.format("Successfully imported Town %s - %d",
                    townSeedDto.getName(), townSeedDto.getPopulation()))
                    .append(System.lineSeparator());
        }
        return this.output.toString();
    }

    @Override
    public Town getTownByName(String townName){
        return this.townRepository.getTownByName(townName).orElse(null);
    }
}
