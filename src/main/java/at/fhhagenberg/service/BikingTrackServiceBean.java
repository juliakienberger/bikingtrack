package at.fhhagenberg.service;

import at.fhhagenberg.exceptions.InvalidRequestBodyException;
import at.fhhagenberg.exceptions.ResourceNotFoundException;
import at.fhhagenberg.model.BikingTrack;
import at.fhhagenberg.model.Coordinate;
import at.fhhagenberg.repository.BikingTrackRepository;
import at.fhhagenberg.repository.CoordinateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Collection;

@Service
public class BikingTrackServiceBean implements BikingTrackService {

    private final BikingTrackRepository bikingTrackRepository;
    private final CoordinateRepository coordinateRepository;

    @Autowired
    public BikingTrackServiceBean(BikingTrackRepository bikingTrackRepository, CoordinateRepository coordinateRepository) {
        this.bikingTrackRepository = bikingTrackRepository;
        this.coordinateRepository = coordinateRepository;
    }

    @Override
    public BikingTrack create(BikingTrack track) {

        // Ensure the entity object to be created does NOT exist in the
        // repository. Prevent the default behavior of save() which will update
        // an existing entity if the entity matching the supplied id exists.
        if (track.getId() != null) {
            // Cannot create DiaryEntry with specified ID value
            throw new EntityExistsException("The id attribute must be null to persist a new entity.");
        }

        // Finalized always should be false when creating new tracks
        track.setFinalized(false);

        return bikingTrackRepository.save(track);
    }

    @Override
    public Collection<BikingTrack> getAllBikingTracks() {
        return bikingTrackRepository.findAll();
    }

    @Override
    public BikingTrack getBikingTrack(Long trackId) {
        return bikingTrackRepository.findById(trackId)
                .orElseThrow(() -> new ResourceNotFoundException("BikingTrack with trackId " + trackId + " not found"));
    }

    @Override
    public BikingTrack finalizeBikingTrack(Long trackId) {
        return bikingTrackRepository.findById(trackId)
                .map(track -> {
                    track.setFinalized(true);
                    return bikingTrackRepository.save(track);
                }).orElseThrow(() -> new ResourceNotFoundException("BikingTrack with trackId " + trackId + " not found"));
    }

    @Override
    public Coordinate addCoordinateToBikingTrack(Coordinate coordinate, Long trackId) {
        if (coordinate.getBikingTrack() != null) {
            // Cannot create DiaryEntry with specified ID value
            throw new InvalidRequestBodyException("The BikingTrack the coordinate belongs to should not be posted in the request body," +
                    "only post the trackId within the URL");
        }

        return bikingTrackRepository.findById(trackId)
                .map(track -> {
                    coordinate.setBikingTrack(track);
                    return coordinateRepository.save(coordinate);
                }).orElseThrow(() -> new ResourceNotFoundException("BikingTrack with trackId " + trackId + " not found"));
    }
}
