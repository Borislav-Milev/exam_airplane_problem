package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@XmlRootElement(name = "passenger")
@XmlAccessorType(XmlAccessType.FIELD)
public class PassengerDto {

    @Expose
    @XmlElement
    @Email
    private String email;
}
