package softuni.exam.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import softuni.exam.models.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "towns", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Town extends BaseEntity {
    @Column(length = 100)
    private String name;
    private Integer population;
    @Column(columnDefinition = "TEXT")
    private String guide;
}
