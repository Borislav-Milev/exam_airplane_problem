package softuni.exam.models.dto.seed;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import softuni.exam.config.LocalDateTimeAdapter;
import softuni.exam.models.dto.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@Getter
@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketSeedDto {

    @Length(min = 2)
    @XmlElement(name = "serial-number")
    private String serialNumber;

    @Positive
    @XmlElement
    private Integer price;

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @XmlElement(name = "take-off")
    private LocalDateTime takeoff;

    @NotNull
    @XmlElement(name = "from-town")
    private FromTownDto fromTown;

    @NotNull
    @XmlElement(name = "to-town")
    private ToTownDto toTown;

    @NotNull
    @XmlElement(name = "passenger")
    private PassengerDto passengerDto;

    @NotNull
    @XmlElement(name = "plane")
    private PlaneDto planeDto;
}
