package gamification.pintourist.pintourist;

/**
 * Created by Marco on 02/07/2015.
 */

import android.content.Context;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;


/**
 * Created by Daniel on 01/06/2015.
 */
public class MapViewer {

    public GoogleMap mMap;
    private Context context;

    public MapViewer(){
        context = MapsActivity.getAppContext();
        setUpMapIfNeeded();
    }

    public void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) MapsActivity.fragmentManager.findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                //setUpMap();
            }
        }

    }


   /*public void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(MapsActivity.getMyLocation().getLatitude(), MapsActivity.getMyLocation().getLongitude())).title("Marker"));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(MapsActivity.getMyLocation().getLatitude(), MapsActivity.getMyLocation().getLongitude()))      // Sets the center of the map to mi position
                .zoom(25)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }*/

    public void moveCameraTo(LatLng nPos, int nZoom){
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(nPos)      // Sets the center of the map to mi position
                .zoom(nZoom)                   // Sets the zoom
                .bearing(0)                // Sets the orientation of the camera to east
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }


    //public void addMarker(MarkerOptions newMarker){
    //   mMap.addMarker(newMarker);
    //}



    public GoogleMap getmMap(){return this.mMap;}
}
