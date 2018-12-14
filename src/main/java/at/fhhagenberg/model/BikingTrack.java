package at.fhhagenberg.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "biking_track")
public class BikingTrack implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    private boolean isFinalized;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "bikingTrack")
    @JsonManagedReference
    private List<Coordinate> coordinateList;

    public BikingTrack() {

    }

    public BikingTrack(BikingTrack track) {
        this.id = track.id;
        this.name = track.name;
        this.isFinalized = track.isFinalized;
        this.coordinateList = track.coordinateList;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isFinalized() {
        return isFinalized;
    }

    public void setFinalized(boolean finalized) {
        isFinalized = finalized;
    }

    public List<Coordinate> getCoordinateList() {
        return coordinateList;
    }
}
