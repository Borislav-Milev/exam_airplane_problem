package softuni.exam.models.dto.seed;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Positive;

@Getter
public class TownSeedDto {

    @Expose
    @Length(min = 2)
    private String name;

    @Expose
    @Positive
    private Integer population;

    @Expose
    private String guide;
}
