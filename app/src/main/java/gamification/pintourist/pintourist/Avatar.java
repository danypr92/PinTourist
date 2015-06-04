package gamification.pintourist.pintourist;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Daniel on 04/06/2015.
 */
public class Avatar {

    private static MarkerOptions mMarker;

    public Avatar(){
        mMarker = new MarkerOptions()
                .position(new LatLng(41.891232, 12.492266))
                .draggable(true)
                //.icon(R.drawable.uomino)
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
