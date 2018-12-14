package at.fhhagenberg.repository;

import at.fhhagenberg.model.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {

}
