package earthquakes.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Location{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long placeId;
    private String name;
    private double lat;
    private double lon;
    private String uid;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public long getPlaceId(){ return placeId;}
    public void setPlaceId(Long id){ this.placeId = id;}

    public String getName(){return name;}
    public void setName(String x){this.name = x;}

    public double getLat(){return lat;}
    public void setLat(double lat){this.lat = lat;}

    public double getLon(){return lon;}
    public void setLon(double lon){this.lon = lon;}

    public String getUid() { return uid; }
    public void setUid (String id) { this.uid = id; }
}
