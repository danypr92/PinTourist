package gamification.pintourist.pintourist;

import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Tile;

import java.util.ArrayList;

/**
 * Created by Daniel on 04/06/2015.
 */
public class Zona {
    private int id;
    private Pin[] pins_CurrentZone;
    private LatLng[] vertices_of_perimeter;
    private int zone_Color;
    private PolygonOptions mPolygonOptions;

    //_______________________________________________________________________________________________________________
    //COSTRUTTORE CONSIGLIATO
    public Zona(int id, Pin[] pins_CurrentZone, PolygonOptions mPolygonOptions) {
        this.id = id;
        this.pins_CurrentZone = pins_CurrentZone;
        this.mPolygonOptions = mPolygonOptions;
        this.vertices_of_perimeter = mPolygonOptions.getPoints().toArray(new LatLng[mPolygonOptions.getPoints().size()]);
        this.zone_Color = mPolygonOptions.getFillColor();
    }
//________________________________________________________________________________________________________________

    public Zona(int id, Pin[] pins_CurrentZone, LatLng[] vertices_of_perimeter) {
        this.id = id;
        this.pins_CurrentZone = pins_CurrentZone;
        this.vertices_of_perimeter = vertices_of_perimeter;
        this.zone_Color = 0x96969655;
        this.mPolygonOptions = new PolygonOptions()
                .add(this.vertices_of_perimeter)
                .strokeWidth(5)
                .strokeColor(this.zone_Color + 0xFF)
                .fillColor(this.zone_Color);
    }

    public Zona(int id, Pin[] pins_CurrentZone, LatLng[] vertices_of_perimeter, int zone_Color) {
        this.id = id;
        this.pins_CurrentZone = pins_CurrentZone;
        this.vertices_of_perimeter = vertices_of_perimeter;
        this.zone_Color = zone_Color;
    }
//_______________________________________________________________________________________________________________

    //get methods
    public int getId() {
        return this.id;
    }

    public Pin[] getPins_CurrentZone() {
        return this.pins_CurrentZone;
    }

    public LatLng[] getvertices_of_perimeter() {
        return this.vertices_of_perimeter;
    }

    public int getZone_Color() {
        return this.zone_Color;
    }

    public PolygonOptions getmPolygonOptions() {
        return this.mPolygonOptions;
    }
//_______________________________________________________________________________________________________________

    //set methods
    public void setId(int id) {
        this.id = id;
    }

    public void setPins_CurrentZone(Pin[] pins_CurrentZone) {
        this.pins_CurrentZone = pins_CurrentZone;
    }

    private void setVertices_of_perimeter(LatLng[] vertices_of_perimeter) {
        this.vertices_of_perimeter = vertices_of_perimeter;
    }

    private void setZone_Color(int zone_Color) {
        this.zone_Color = zone_Color;
    }

    public void setmPolygonOptions(PolygonOptions mPolygonOptions) {
        this.mPolygonOptions = mPolygonOptions;
        this.setVertices_of_perimeter(mPolygonOptions.getPoints().toArray(new LatLng[mPolygonOptions.getPoints().size()]));
        this.setZone_Color(mPolygonOptions.getFillColor());
    }
    //_______________________________________________________________________________________________________________

    public void draw() {
        GoogleMap map = MapsActivity.getmMapViewer().getmMap();
        map.addPolygon(this.mPolygonOptions);
        if (this.pins_CurrentZone != null) {
            for (Pin p : this.pins_CurrentZone)
                Utility.markers.add(map.addMarker(p.getPinMarker()));
        }
    }
}
