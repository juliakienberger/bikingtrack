package at.fhhagenberg.service;

import at.fhhagenberg.model.BikingTrack;
import at.fhhagenberg.model.Coordinate;

import java.util.Collection;

public interface BikingTrackService {

    BikingTrack create(BikingTrack track);

    Collection<BikingTrack> getAllBikingTracks();

    BikingTrack getBikingTrack(Long trackId);

    BikingTrack finalizeBikingTrack(Long trackId);

    Coordinate addCoordinateToBikingTrack(Coordinate coordinate, Long trackId);

}
