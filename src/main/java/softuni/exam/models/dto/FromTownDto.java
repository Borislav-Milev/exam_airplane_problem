package softuni.exam.models.dto;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "from-town")
public class FromTownDto extends TownDto {
}
