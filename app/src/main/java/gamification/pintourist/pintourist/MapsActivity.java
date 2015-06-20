package gamification.pintourist.pintourist;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import gamification.pintourist.pintourist.Utility;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MapsActivity extends ActionBarActivity {

    private Toolbar toolbar;
    //Avatar user
    private static Avatar mAvatar;
    private static LatLng mLocation;

    //Map Viewer & gestire fragment della mappa
    private static MapViewer mMapViewer;
    public static FragmentManager fragmentManager;


    //Pin Obiettivo
    private static Pin mPinTarget;
    private static Context context;



    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_map);

        /*

        // Set a toolbar to replace the action bar.
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        */

        // initialising the object of the FragmentManager. Here I'm passing getSupportFragmentManager().
        // You can pass getFragmentManager() if you are coding for Android 3.0 or above.
        fragmentManager = getSupportFragmentManager();

        context = getApplicationContext();
        mMapViewer = new MapViewer();
        mAvatar = new Avatar();

        //mMarkers = mParser.parse();
        //setUpMapIfNeeded();
        mMapViewer.setUpMapIfNeeded();
        mMapViewer.addMarker(mAvatar.getMarker());
        mMapViewer.moveCameraTo(mAvatar.getLatLng(), 30);

        Utility.ZonaRioneMonti.draw();
        Utility.ZonaSanLorenzo.draw();





        startGame();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //mLocationListener.update();
        //setUpMapIfNeeded();
        mMapViewer.setUpMapIfNeeded();
    }


    public static Context getAppContext(){
        return  context;
    }

    public static LatLng getMyLocation(){
        return mAvatar.getLatLng();
    }

    public static Pin getPinTarget(){
        return mPinTarget;
    }

    public static MapViewer getmMapViewer() {return mMapViewer;}

    public void startGame(){
        TextView Suggeritore=(TextView) findViewById(R.id.suggeritore);
        Suggeritore.setText(R.string.scegliPinPartenza);


    }

}
