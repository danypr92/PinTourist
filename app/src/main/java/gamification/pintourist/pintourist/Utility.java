package gamification.pintourist.pintourist;

import android.content.Context;
import android.content.res.Resources;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;

/*
Resources.getSystem()
*/

/**
 * Created by Roberto on 19/06/15.
 */
public class Utility {
    //Generics
    static final String Avatartitle="Avatar";
    static final int MIN_DSTANCE=20;


    //Pin_______________________________________________________________________________________________________________________
    static final Context context = MapsActivity.getAppContext();
    static Resources myR = context.getResources();//Resources.getSystem();
    static final Pin Verano = new Pin (context.getString(R.string.verano), 41.902932, 12.525009,
          // new Indizio(myR.getStringArray(R.array.indiziVerano)));
            null);
    static final Pin Minerva = new Pin (context.getString(R.string.minerva),41.902901, 12.514556);
    static final Pin BasilicaSanLorenzo = new Pin (context.getString(R.string.basilicaSanLorenzo),41.9025562,12.5207542);


    static final Pin[] pinZonaSanLorenzo=new Pin[]{
            Verano,
            Minerva,
            BasilicaSanLorenzo
    };



    //Zone_______________________________________________________________________________________________________________________

    //Problema con i colori, si vedono molto scuri sulla mappa. Se volete provare il vero colore, prendete il valore
    //nel file colors.xml al colore corrispondente e lo inserite manualmente nella costruzione della Zona.
    // Dovete sostituire '#'--> '0x'.
    //Se, come volevo fare io, prendete i valori dei colori come risorsa dal file colors.xml, si vedono molto scuri...
    //Ignoro il perche'.

    //****Global variables for Zones implementation****
    static final int STROKE_WIDTH=5;
    //N.B. DELTA_STROKE_COLOR: colore del contorno del poligono,
    // e' definito come differenza rispetto il colore della zona (per esempio un po' piu' scuro)
    //Forse e' meglio dichiarare un'altro colore... DEPRECATED xD
    static final int DELTA_STROKE_COLOR=0x334444;


    /*
    ****Dichiarazione elementi della Zona****
        Organizzato in:
           - id della mappa (int);
           - Coordinate vertici (LatLng[]);
           - Colore zona (int in formato 0x)
           -Colore contorno per la zona (int in formato 0x)
           - Opzioni per il poligono (PolygonOptions). per ora modifichiamo solo:
                a. coordinate vertici
                b. la larghezza del contorno del poligono (strokeWidth);
                c. il colore del contorno (stroke color);
                d. il colore di riempimento (fill color) ;
    */

    //vecchia zona del Colosseo.
    /*
            //Colosseo --> 41.891232, 12.492266
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
     */

    //Elementi Zona Rione Monti (id 0)

    static final LatLng[] verticiZonaRioneMonti= {
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
    static final int idZonaRioneMonti= 0;
    //static final int coloreZonaRioneMonti=R.color.transparentRed;
    static final int coloreZonaRioneMonti=0x77FF4444;
    static final int coloreContornoZonaRioneMonti=R.color.darkred;
    static final PolygonOptions polOpZonaRioneMonti=new PolygonOptions()
            .add(verticiZonaRioneMonti)
            .strokeWidth(STROKE_WIDTH)
            .strokeColor(coloreContornoZonaRioneMonti)
            .fillColor(coloreZonaRioneMonti);


    //Elementi Zona San Lorenzo (id 1)

    static final LatLng[] verticiZonaSanLorenzo= {
        new LatLng(41.892853677798335, 12.541837692260742),
                new LatLng(41.896335692597106, 12.541279792785645),
                new LatLng(41.89901895063197, 12.537803649902344),
                new LatLng(41.902851980824636, 12.536044120788574),
                new LatLng(41.904289307827774, 12.53612995147705),
                new LatLng(41.90805814497171, 12.534198760986328),
                new LatLng(41.90828171306592, 12.531452178955078),
                new LatLng(41.90879272291262, 12.529950141906738),
                new LatLng(41.907642945005236, 12.524714469909668),
                new LatLng(41.90352273745264, 12.520208358764648),
                new LatLng(41.906237632773966, 12.516603469848633),
                new LatLng(41.90553496505796, 12.515702247619629),
                new LatLng(41.905542949961806, 12.515605688095093),
                new LatLng(41.904113636263034, 12.510842084884644),
                new LatLng(41.90400184501563, 12.510745525360107),
                new LatLng(41.902772128375744, 12.507022619247437),
                new LatLng(41.898627649190374, 12.509243488311768),
                new LatLng(41.897972812842845, 12.50942587852478),
                new LatLng(41.89659124583868, 12.510895729064941),
                new LatLng(41.89659923186099, 12.511206865310669),
                new LatLng(41.89416344875709, 12.513953447341919),
                new LatLng(41.89338477718664, 12.514715194702148),
                new LatLng(41.892905589963355, 12.515852451324463),
                new LatLng(41.892262674791525, 12.515358924865723),
                new LatLng(41.89171958612617, 12.516249418258667),
                new LatLng(41.891479986717634, 12.5169038772583),
                new LatLng(41.89155186663458, 12.518373727798462),
                new LatLng(41.89136018667637, 12.521345615386963),
                new LatLng(41.89107266566053, 12.524993419647217),
                new LatLng(41.89116051944157, 12.526527643203735),
                new LatLng(41.89219878224704, 12.532289028167725),
                new LatLng(41.892494284729565, 12.536258697509766),
                new LatLng(41.892398446236385, 12.539520263671875),
                new LatLng(41.89284569130767, 12.541859149932861),
    };
    static final int idZonaSanLorenzo= 1;
    //static final int coloreZonaSanLorenzo=R.color.transparentBlue;
    static final int coloreZonaSanLorenzo=0x7733B5E5;
    static final int coloreContornoZonaSanLorenzo=R.color.darkblue;
    static final PolygonOptions polOpZonaSanLorenzo=new PolygonOptions()
            .add(verticiZonaSanLorenzo)
            .strokeWidth(STROKE_WIDTH)
            .strokeColor(coloreContornoZonaSanLorenzo)
            .fillColor(coloreZonaSanLorenzo);

    //Dichiarazione Zone


    static final Zona ZonaSanLorenzo=new Zona(
            idZonaSanLorenzo,
            pinZonaSanLorenzo,
            polOpZonaSanLorenzo);

    static final Zona ZonaRioneMonti=new Zona(
            idZonaRioneMonti,
            null,
            polOpZonaRioneMonti);



}
