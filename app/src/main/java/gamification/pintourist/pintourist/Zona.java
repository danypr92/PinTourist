package gamification.pintourist.pintourist;

import android.location.Location;

/**
 * Created by Daniel on 04/06/2015.
 */
public class Zona {
    private int id;
    private Pin[] pins_CurrentZone;
    private Location[] angles_of_perimeter;

    public Zona(int id,Pin[] pins_CurrentZone, Location[] angles_of_perimeter) {
        this.id=id;
        this.pins_CurrentZone=pins_CurrentZone;
        this.angles_of_perimeter=angles_of_perimeter;
    }

    public int getId(){ return this.id;}
    public Pin[] getPins_CurrentZone(){ return this.pins_CurrentZone;}
    public Location[] getAngles_of_perimeter(){ return this.angles_of_perimeter;}

    public void setId(int id) { this.id=id; }
    public void setPins_CurrentZone(Pin[] pins_CurrentZone) { this.pins_CurrentZone=pins_CurrentZone; }
    public void setAngles_of_perimeter(Location[] angles_of_perimeter) { this.angles_of_perimeter=angles_of_perimeter; }



}
