package at.fhhagenberg.repository;

import at.fhhagenberg.model.BikingTrack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikingTrackRepository extends JpaRepository<BikingTrack, Long> {

}