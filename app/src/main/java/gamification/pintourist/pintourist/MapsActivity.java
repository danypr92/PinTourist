package gamification.pintourist.pintourist;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import gamification.pintourist.pintourist.MapViewer;
import gamification.pintourist.pintourist.R;
//import gamification.pintourist.pintourist.Avatar;
import gamification.pintourist.pintourist.meccanica.*;


public class MapsActivity extends FragmentActivity {

    //Map Viewer & gestire fragment della mappa
    private static MapViewer mMapViewer;
    public static FragmentManager fragmentManager;
    private static Context context;
    public static TextView suggeritore;
    //___________________________________

    //Meccanica
    private static Pin mPinTarget;
    private static Avatar mAvatar;
    public static GamePhase gamePhase=GamePhase.PIN_CHOICE;


    /*
    public static Avatar mAvatar;
    private static LatLng mLocation;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_map);

        this.context=getApplicationContext();
        this.suggeritore=(TextView) findViewById(R.id.suggeritore);

        // initialising the object of the FragmentManager. Here I'm passing getSupportFragmentManager().
        // You can pass getFragmentManager() if you are coding for Android 3.0 or above.
        fragmentManager = getSupportFragmentManager();

        mMapViewer = new MapViewer();
        mMapViewer.setUpMapIfNeeded();

        mAvatar = new Avatar();
        mAvatar.addAvatar();
        mMapViewer.moveCameraTo(mAvatar.getAvatarMarkerOptions().getPosition(), 500);

        Utility.ZonaRioneMonti.draw();
        Utility.ZonaSanLorenzo.draw();

        startGame();

    }

    // _______________________________________________________________________________________________
//NOSTRI METODI

    public static Context getAppContext(){
        return  context;
    }
    public static MapViewer getmMapViewer() {return mMapViewer;}
    public static Pin getPinTarget(){
        return mPinTarget;
    }
    public static Avatar getmAvatar() {
        return mAvatar;
    }

    public void startGame(){
        suggeritore.setText(R.string.scegliPinPartenza);
        //for (final Pin p: Utility.ZonaSanLorenzo.getPins_CurrentZone()){
        mMapViewer.getmMap().setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (gamePhase== GamePhase.PIN_CHOICE) {
                    for (Pin p: Utility.ZonaSanLorenzo.getPins_CurrentZone()){
                        if (p.getPinMarker().equals(marker)){
                            MapsActivity.mPinTarget=p;
                        }
                    }
                    MapsActivity.mPinTarget.getPinMarker().setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                    //Utility.markers.get(markerId);
                    Toast.makeText(MapsActivity.this, "You have selected the Pin with id: ", Toast.LENGTH_LONG).show();
                    suggeritore.setText(R.string.arrivaAlPin);
                    gamePhase=GamePhase.PIN_DISCOVERING;
                    //setupPopupIndizi();
                }
                else if (gamePhase==GamePhase.PIN_DISCOVERING){
                    if (MapsActivity.mPinTarget.getPinMarker() != marker ){
                        //TODO: Immagine dialogo per confermare il cambio Pin Obiettivo: per ora non lo implementiamo
                        Toast.makeText(MapsActivity.this, "You have selected the Pin with id: " + " but the target Pin was already selected", Toast.LENGTH_LONG).show();
                    }
                    else{ //Pin Target == Pin premuto:
                        if (MapsActivity.mPinTarget.isIlluminato()){ //Sei vicino?
                            //setupPopupSfidaPrimaSchermata();
                        }
                    }
                }
                //Toast.makeText(MapsActivity.this, "", Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }
}
