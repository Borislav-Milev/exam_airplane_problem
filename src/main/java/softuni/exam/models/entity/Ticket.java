package softuni.exam.models.entity;

import lombok.*;
import softuni.exam.models.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tickets", uniqueConstraints = @UniqueConstraint(columnNames = "serialNumber"))
public class Ticket extends BaseEntity {

    @Column(length = 50)
    private String serialNumber;
    private Integer price;
    private LocalDateTime takeoff;

    @ManyToOne
    @ToString.Exclude
    private Town fromTown;

    @ManyToOne
    @ToString.Exclude
    private Town toTown;

    @ManyToOne
    @ToString.Exclude
    private Passenger passenger;

    @ManyToOne
    @ToString.Exclude
    private Plane plane;
}
