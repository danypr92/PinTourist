package gamification.pintourist.pintourist;

import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;

/**
 * Created by Daniel on 04/06/2015.
 */
public class Zona {
    private int id;
    private Pin[] pins_CurrentZone;
    private LatLng[] angles_of_perimeter;
    private int zone_Color;

    public Zona(int id,Pin[] pins_CurrentZone, LatLng[] angles_of_perimeter) {
        this.id=id;
        this.pins_CurrentZone=pins_CurrentZone;
        this.angles_of_perimeter=angles_of_perimeter;
        this.zone_Color=0xFFFFFFFF;
    }

    public Zona(int id,Pin[] pins_CurrentZone, LatLng[] angles_of_perimeter,int zone_Color) {
        this.id=id;
        this.pins_CurrentZone=pins_CurrentZone;
        this.angles_of_perimeter=angles_of_perimeter;
        this.zone_Color=zone_Color;
    }

    public int getId(){ return this.id;}
    public Pin[] getPins_CurrentZone(){ return this.pins_CurrentZone;}
    public LatLng[] getAngles_of_perimeter(){ return this.angles_of_perimeter;}
    public int getZone_Color(){return this.zone_Color;}

    public void setId(int id) { this.id=id; }
    public void setPins_CurrentZone(Pin[] pins_CurrentZone) { this.pins_CurrentZone=pins_CurrentZone; }
    public void setAngles_of_perimeter(LatLng[] angles_of_perimeter) { this.angles_of_perimeter=angles_of_perimeter; }
    public void setZone_Color(int zone_Color){ this.zone_Color=zone_Color;}

    public void draw(){
        GoogleMap map = MapsActivity.getmMapViewer().getmMap();
        PolygonOptions polygonOptions=new PolygonOptions().add(this.angles_of_perimeter);
        polygonOptions.fillColor(this.zone_Color);
        map.addPolygon(polygonOptions);
    }
}
