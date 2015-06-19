package gamification.pintourist.pintourist;


import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by Daniel on 04/06/2015.
 */
public class Pin {

    public static final int NUMERO_DOMANDE = 3;

    private MarkerOptions pinMarker;
    public boolean conquistato;
    private String nome;
    private Indizio indizi;

    public Pin(String nome, double Lat, double Long, Indizio lista_indizi){
        pinMarker = new MarkerOptions().position(new LatLng(Lat, Long)).title("Scoprimi");
        conquistato = false;
        this.nome = nome;
        this.indizi = lista_indizi;
    }

    // costruttore temporaneo
    public Pin(String nome, double Lat, double Long) {
        pinMarker = new MarkerOptions().position(new LatLng(Lat, Long)).title("Scoprimi");
        conquistato = false;
        this.nome = nome;
    }
    public String getNome() { return nome; }

    public MarkerOptions getPinMarker() { return pinMarker; }

    //return lat and long of the pin
    public LatLng getLatLng() { return getPinMarker().getPosition(); }

    public Indizio getIndizi() {
        return indizi;
    }

    public boolean isConquistato() {
        return conquistato;
    }

    public void setConquistato() {
        this.conquistato = true;
    }

    //setting the snippet as the name of the monument after the user's answered the question
    public void setName (){
        if(this.isConquistato())
            this.pinMarker.title(this.getNome());
    }

    private static int generatoreRandom(){
        Random r = new Random();
        return r.nextInt(NUMERO_DOMANDE);
    }


}