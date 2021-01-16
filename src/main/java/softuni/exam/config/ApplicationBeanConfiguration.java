package softuni.exam.config;

import com.google.gson.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softuni.exam.models.dto.TownDto;
import softuni.exam.models.entity.Town;
import softuni.exam.util.FileReaderImpl;
import softuni.exam.util.contract.FileReader;
import softuni.exam.util.contract.ValidationUtil;
import softuni.exam.util.ValidationUtilImpl;
import softuni.exam.util.XmlParserImpl;
import softuni.exam.util.contract.XmlParser;



@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public XmlParser xmlParser(){
        return new XmlParserImpl();
    }
    @Bean
    public FileReader fileReader(){
        return new FileReaderImpl();
    }
    @Bean
    public StringBuilder output(){
        return new StringBuilder();
    }
}
