package gamification.pintourist.pintourist;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends ActionBarActivity {

    private static Toolbar toolbar;

    //Avatar user
    private static Avatar mAvatar;
    private static LatLng mLocation;

    //Map Viewer & gestire fragment della mappa
    private static MapViewer mMapViewer;
    public static FragmentManager fragmentManager;

    //Lista de zone
    private List<Zona> mZone;
    //Zona di test
    private Zona mZonaColosseo;
    private Zona ZonaRioneMonti;

    private LatLng[] VerticiZonaRioneMonti= {
            new LatLng(41.89534541412743, 12.483129501342773),
            new LatLng(41.89601624960714, 12.486777305603027),
            new LatLng(41.89895506484607, 12.487077713012695),
            new LatLng(41.9015423880521, 12.490768432617188),
            new LatLng(41.898380089897174, 12.496905326843262),
            new LatLng(41.897932883580076, 12.496604919433594),
            new LatLng (41.89668707804063, 12.498278617858887),
            new LatLng(41.89668707804063, 12.499265670776367),
            new LatLng(41.887135094867865, 12.5044584274292),
            new LatLng(41.8872948387714, 12.503299713134766),
            new LatLng(41.88889225583803, 12.49845027923584),
            new LatLng(41.8899784766276, 12.494630813598633),
            new LatLng(41.888956151689875, 12.49368667602539),
            new LatLng(41.88831719029552, 12.490510940551758),
            new LatLng(41.88489863827785, 12.489781379699707),
            new LatLng(41.88374843011852, 12.488493919372559),
            new LatLng(41.887806016578544, 12.481670379638672),
            new LatLng(41.89195918463598, 12.480597496032715),
            new LatLng(41.89349259381646, 12.482185363769531),
            new LatLng(41.895249579912274, 12.483172416687012)
    };



    //Pin Obiettivo
    private static Pin mPinTarget;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_map);

        // initialising the object of the FragmentManager. Here I'm passing getSupportFragmentManager(). You can pass getFragmentManager() if you are coding for Android 3.0 or above.
        fragmentManager = getSupportFragmentManager();

        context = getApplicationContext();
        //Colosseo --> 41.891232, 12.492266
        mMapViewer = new MapViewer();
        mAvatar = new Avatar();

        //esempio zona vicino Colosseo
                  double k= 0.000500;
        double LatColosseo=41.891232;
        double LngColosseo=12.492266;
        LatLng[] VerticiZonaColosseo= {
                new LatLng(LatColosseo - k, LngColosseo - k),
                new LatLng(LatColosseo - k, LngColosseo + k),
                new LatLng(LatColosseo + k, LngColosseo + k),
                new LatLng(LatColosseo + k, LngColosseo - k)
        };
        mZonaColosseo=new Zona(0,null,VerticiZonaColosseo);

        ZonaRioneMonti=new Zona(0,null,VerticiZonaRioneMonti);
        //mMarkers = mParser.parse();
        //setUpMapIfNeeded();
        mMapViewer.setUpMapIfNeeded();
        mMapViewer.addMarker(mAvatar.getMarker());
        mMapViewer.moveCameraTo(mAvatar.getLatLng(), 30);


        mZonaColosseo.draw();
        ZonaRioneMonti.draw();
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
}
