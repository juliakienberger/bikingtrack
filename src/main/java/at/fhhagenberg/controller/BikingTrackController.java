package at.fhhagenberg.controller;

import at.fhhagenberg.model.BikingTrack;
import at.fhhagenberg.model.Coordinate;
import at.fhhagenberg.service.BikingTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
public class BikingTrackController {

    private final BikingTrackService bikingTrackService;

    @Autowired
    public BikingTrackController(BikingTrackService bikingTrackService) {
        this.bikingTrackService = bikingTrackService;
    }

    @RequestMapping(value = "/api/track", method = RequestMethod.GET)
    public ResponseEntity<Collection<BikingTrack>> getAllBikingTracks() {
        Collection<BikingTrack> tracks = bikingTrackService.getAllBikingTracks();
        return new ResponseEntity<>(tracks, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/track/{id}", method = RequestMethod.GET)
    public ResponseEntity<BikingTrack> getBikingTrackForId(@PathVariable Long id) {
        BikingTrack track = bikingTrackService.getBikingTrack(id);
        return new ResponseEntity<>(track, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/track", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BikingTrack> createBikingTrack(@RequestBody BikingTrack track) {
        BikingTrack savedTrack = bikingTrackService.create(track);
        return new ResponseEntity<>(savedTrack, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/track/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Coordinate> createCoordinateForTrackWithId(@RequestBody Coordinate coordinate, @PathVariable Long id) {
        Coordinate savedCoordinate = bikingTrackService.addCoordinateToBikingTrack(coordinate, id);
        return new ResponseEntity<>(savedCoordinate, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/track/{id}/finalize", method = RequestMethod.PUT)
    public ResponseEntity<BikingTrack> finalizeBikingTrackForId(@PathVariable Long id) {
        BikingTrack track = bikingTrackService.finalizeBikingTrack(id);
        return new ResponseEntity<>(track, HttpStatus.OK);
    }
}
