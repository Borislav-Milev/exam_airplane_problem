package softuni.exam.models.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import softuni.exam.models.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "passengers", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Passenger extends BaseEntity {

    @Column(length = 20)
    private String firstName;
    @Column(length = 20)
    private String lastName;
    @Column(columnDefinition = "TINYINT UNSIGNED")
    private Short age;
    @Column(length = 20)
    private String phoneNumber;
    @Column(length = 50)
    private String email;

    @ManyToOne
    @ToString.Exclude
    private Town town;

    @OneToMany(mappedBy = "passenger", fetch = FetchType.EAGER)
    private Set<Ticket> tickets = new HashSet<>();
}
