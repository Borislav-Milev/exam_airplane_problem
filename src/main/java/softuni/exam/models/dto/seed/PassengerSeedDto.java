package softuni.exam.models.dto.seed;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import softuni.exam.models.dto.PassengerDto;
import softuni.exam.models.dto.TownDto;

import javax.validation.constraints.Positive;


@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
public class PassengerSeedDto extends PassengerDto {

    @Expose
    @Length(min = 2)
    private String firstName;

    @Expose
    @Length(min = 2)
    private String lastName;

    @Expose
    @Positive
    private Short age;

    @Expose
    private String phoneNumber;

    @Expose
    @Length(min = 2)
    private String town;

}
