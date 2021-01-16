package softuni.exam.models.dto.seed;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import softuni.exam.models.dto.PlaneDto;

import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@XmlRootElement(name = "plane")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlaneSeedDto extends PlaneDto {

    @Positive
    @XmlElement
    private Short capacity;

    @Length(min = 2)
    @XmlElement
    private String airline;
}
