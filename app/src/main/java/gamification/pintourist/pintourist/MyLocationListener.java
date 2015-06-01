package gamification.pintourist.pintourist;


import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by Daniel on 21/05/2015.
 */
public class MyLocationListener implements LocationListener {

    public static final long TIEMPO_MIN = 10 * 1000 ; // 10 segundos
    public static final long DISTANCIA_MIN = 5 ; // 5 metros
    
    public static LocationManager mLocationManager;
    public static String mProvider;

    public Location mLoc;
    
    public MyLocationListener(){

        mLocationManager = (LocationManager)MapsActivity.getAppContext().getSystemService(Context.LOCATION_SERVICE);
        setProvider();

        mLocationManager.requestLocationUpdates(mProvider, TIEMPO_MIN, DISTANCIA_MIN, this);
        mLoc = mLocationManager.getLastKnownLocation(mProvider);
        
    };

    public void update(){
        mLocationManager.requestLocationUpdates(mProvider, TIEMPO_MIN, DISTANCIA_MIN, this);
    }

    private void setProvider(){
        Criteria mCriteria = new Criteria();
        mCriteria.setCostAllowed(false);
        mCriteria.setAltitudeRequired(false);
        mCriteria.setAccuracy(Criteria.ACCURACY_FINE);
        mProvider = mLocationManager.getBestProvider(mCriteria, true);
    }
    
    // Metodi di interfaccia LocationListener
    public void onLocationChanged(Location location) {
        mLoc = mLocationManager.getLastKnownLocation(mProvider);

    }

    public void onProviderDisabled(String mProvider) {
        Intent intent = new Intent( android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        MapsActivity.getAppContext().startActivity(intent);
    }

    public void onProviderEnabled(String mProvider) {}

    public void onStatusChanged(String mProvider, int state,Bundle extras) {}



}
