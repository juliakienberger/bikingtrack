package at.fhhagenberg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "coordinate")
public class Coordinate {

    @Id
    @GeneratedValue
    private Long coordinateId;

    private double longitude;

    private double latitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "biking_track_id")
    @JsonBackReference
    private BikingTrack bikingTrack;


    public Coordinate() {

    }

    public Coordinate(Coordinate coordinate) {
        this.longitude = coordinate.longitude;
        this.latitude = coordinate.latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setBikingTrack(BikingTrack bikingTrack) {
        this.bikingTrack = bikingTrack;
    }

    public BikingTrack getBikingTrack() {
        return bikingTrack;
    }
}
