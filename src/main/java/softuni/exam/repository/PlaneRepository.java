package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entity.Plane;

import java.util.Optional;

public interface PlaneRepository extends JpaRepository<Plane, Integer> {
    Optional<Plane> getPlaneBySerialNumber(String serialNumber);
}
