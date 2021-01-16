package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.xml.bind.annotation.*;

@Getter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({FromTownDto.class, ToTownDto.class})
public class TownDto {

    @Expose
    @XmlElement
    @Length(min = 2)
    private String name;

}
