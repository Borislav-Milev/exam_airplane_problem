package softuni.exam.models.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import softuni.exam.models.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "planes", uniqueConstraints = @UniqueConstraint(columnNames = "serialNumber"))
public class Plane extends BaseEntity {

    @Column(length = 50)
    private String serialNumber;
    @Column(columnDefinition = "SMALLINT UNSIGNED")
    private Short capacity;
    @Column(length = 50)
    private String airline;
    @OneToMany(mappedBy = "plane", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Ticket> tickets;
}
