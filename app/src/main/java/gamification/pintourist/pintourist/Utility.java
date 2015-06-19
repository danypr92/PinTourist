package gamification.pintourist.pintourist;

import android.content.Context;
import android.content.res.Resources;

import com.google.android.gms.maps.GoogleMap;

/*
Resources.getSystem()
*/

/**
 * Created by Roberto on 19/06/15.
 */
public class Utility {


    static final Context context = MapsActivity.getAppContext();
    static Resources myR = context.getResources();//Resources.getSystem();
    static final Pin Verano = new Pin (context.getString(R.string.verano), 41.902932, 12.525009,
            new Indizio(myR.getStringArray(R.array.indiziVerano)));
    static final Pin Minerva = new Pin (context.getString(R.string.minerva),41.902901, 12.514556);



}
