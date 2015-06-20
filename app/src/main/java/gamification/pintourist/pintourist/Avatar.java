package gamification.pintourist.pintourist;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
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
    }

    public MarkerOptions getMarker(){
        return mMarker;
    }

    public LatLng getLatLng(){
        return mMarker.getPosition();
    }

}
