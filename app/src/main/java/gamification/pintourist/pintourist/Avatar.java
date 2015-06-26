package gamification.pintourist.pintourist;

import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Daniel on 04/06/2015.
 */

public class Avatar {

    private static MarkerOptions mMarker;

    public Avatar(){
        mMarker = new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.avatar_image)) // troppo piccola!
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.avatar_image2)) //troppo grande!
                //.icon(R.drawable.uomino)
                //.position(new LatLng(41.891232, 12.492266))
                .position(new LatLng(41.8990998, 12.517614))
                .draggable(true)
                .title("My Position")
                .snippet("My Position");

        MapsActivity.getmMapViewer().getmMap().setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {
                if (marker.equals(MapsActivity.mAvatar.getMarker())){
                    Toast.makeText(MapsActivity.getAppContext(),"marker avatar == marker options avatar", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MapsActivity.getAppContext(),"marker avatar != marker options avatar", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {

            }
        });
    }

    public MarkerOptions getMarker(){
        return mMarker;
    }

    public LatLng getLatLng(){
        return mMarker.getPosition();
    }

}
