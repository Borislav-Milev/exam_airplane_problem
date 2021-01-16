package softuni.exam.models.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import softuni.exam.models.dto.seed.PlaneSeedDto;

import javax.xml.bind.annotation.*;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "plane")
@XmlSeeAlso(PlaneSeedDto.class)
public class PlaneDto {

    @Length(min = 5)
    @XmlElement(name = "register-number")
    private String serialNumber;
}
