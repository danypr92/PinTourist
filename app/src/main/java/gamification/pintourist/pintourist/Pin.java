package gamification.pintourist.pintourist;


import android.location.Location;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.internal.BitmapDescriptorParcelable;
import com.google.android.gms.maps.model.internal.zzi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by Daniel on 04/06/2015.
 */
public class Pin {

    public static final int NUMERO_DOMANDE = 3;

    private static int autoIncrementalPinId = 0;
    private int pinId;
    private MarkerOptions pinMarker;
    public boolean conquistato;
    public boolean isObbiettivo;
    private String nome;
    private Indizio indizi;


    public Pin(String nome, double Lat, double Long, Indizio lista_indizi) {
        pinMarker = new MarkerOptions().position(new LatLng(Lat, Long)).title("Scoprimi");
        conquistato = false;
        this.nome = nome;
        this.indizi = lista_indizi;
        this.pinId = ++autoIncrementalPinId;
    }

    // costruttore temporaneo
    public Pin(String nome, double Lat, double Long) {
        pinMarker = new MarkerOptions().position(new LatLng(Lat, Long)).title("Scoprimi");
        conquistato = false;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public MarkerOptions getPinMarker() {
        return pinMarker;
    }

    //return lat and long of the pin
    public LatLng getLatLng() {
        return getPinMarker().getPosition();
    }

    public Indizio getIndizi() {
        return indizi;
    }

    public int getPinId() {
        return this.pinId;
    }

    public boolean isConquistato() {
        return conquistato;
    }

    public void setConquistato() {
        this.conquistato = true;
    }

    public void setObbiettivo() {
        this.isObbiettivo = true;
    }

    //setting the snippet as the name of the monument after the user's answered the question
    public void setName() {
        if (this.isConquistato())
            this.pinMarker.title(this.getNome());
    }

    private static int generatoreRandom() {
        Random r = new Random();
        return r.nextInt(NUMERO_DOMANDE);
    }

    public boolean isIlluminato() {
        if (MapsActivity.getPinTarget() != null) {
            Location avatarLocation = new Location("gps");
            avatarLocation.setLatitude(Utility.avatarMarker.getPosition().latitude);
            avatarLocation.setLongitude(Utility.avatarMarker.getPosition().longitude);

            Location pinTargetLocation = new Location("gps");
            pinTargetLocation.setLatitude(MapsActivity.getPinTarget().getPinMarker().getPosition().latitude);
            pinTargetLocation.setLongitude(MapsActivity.getPinTarget().getPinMarker().getPosition().longitude);
            if (avatarLocation.distanceTo(pinTargetLocation) < Utility.MIN_DSTANCE) {
                return true;
            }
            else return false;
        }
        return false;
    }
}