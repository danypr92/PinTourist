package gamification.pintourist.pintourist;

import android.location.Location;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;

/**
 * Created by Daniel on 04/06/2015.
 */

public class Avatar {

    private static MarkerOptions mMarker;

    public Avatar() {
        mMarker = new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.avatar_image)) // troppo piccola!
                        //.icon(BitmapDescriptorFactory.fromResource(R.drawable.avatar_image2)) //troppo grande!
                        //.icon(R.drawable.uomino)
                        //.position(new LatLng(41.891232, 12.492266))
                .position(new LatLng(41.8990998, 12.517614))
                .draggable(true)
                .title(Utility.Avatartitle)
                .snippet("My Position");
        Location avatarLocation = new Location("gps");
        avatarLocation.setLatitude(mMarker.getPosition().latitude);
        avatarLocation.setLongitude(mMarker.getPosition().longitude);
        Toast.makeText(MapsActivity.getAppContext(), avatarLocation.toString(), Toast.LENGTH_LONG).show();


        MapsActivity.getmMapViewer().getmMap().setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
                }

            @Override
            public void onMarkerDrag(Marker marker) {
                if (marker.getTitle().equals(Utility.Avatartitle) && MapsActivity.getPinTarget() != null) {
                    if (MapsActivity.getPinTarget().isIlluminato()) {
                        if (!MapsActivity.Suggeritore.getText().toString().equals(R.string.cliccaSulPinIlluminato))
                            MapsActivity.Suggeritore.setText(R.string.cliccaSulPinIlluminato);
                        //avvia animazione
                    } else {
                        if (!MapsActivity.Suggeritore.getText().toString().equals(R.string.arrivaAlPin))
                            MapsActivity.Suggeritore.setText(R.string.arrivaAlPin);
                    }
                }
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                onMarkerDrag(marker);
                if (MapsActivity.getPinTarget()!=null && MapsActivity.getPinTarget().isIlluminato())
                    Toast.makeText(MapsActivity.getAppContext(),"Illuminato", Toast.LENGTH_LONG).show();
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



//Toast.makeText(MapsActivity.getAppContext(), avatarLocation.distanceTo(pinTargetLocation) + " Sei vicino al Pin Target!", Toast.LENGTH_SHORT).show();
//Toast.makeText(MapsActivity.getAppContext(),"marker avatar != marker options avatar", Toast.LENGTH_LONG).show();
                /*
                if (marker.getTitle().equals(Utility.Avatartitle) && MapsActivity.getPinTarget() != null) {
                    //Toast.makeText(MapsActivity.getAppContext(),"marker avatar == marker options avatar", Toast.LENGTH_LONG).show();
                    Location avatarLocation = new Location("gps");
                    avatarLocation.setLatitude(marker.getPosition().latitude);
                    avatarLocation.setLongitude(marker.getPosition().longitude);

                    Location pinTargetLocation = new Location("gps");
                    pinTargetLocation.setLatitude(MapsActivity.getPinTarget().getPinMarker().getPosition().latitude);
                    pinTargetLocation.setLongitude(MapsActivity.getPinTarget().getPinMarker().getPosition().longitude);
                    Toast.makeText(MapsActivity.getAppContext(), String.valueOf(avatarLocation.distanceTo(pinTargetLocation)), Toast.LENGTH_SHORT).show();
                    if (avatarLocation.distanceTo(pinTargetLocation) < Utility.MIN_DSTANCE) {
                        Toast.makeText(MapsActivity.getAppContext(), avatarLocation.distanceTo(pinTargetLocation) + " Sei vicino al Pin Target!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                    }
                    */