package gamification.pintourist.pintourist;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.location.Location;
import android.media.Image;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import gamification.pintourist.pintourist.Utility;

import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MapsActivity extends FragmentActivity {

    private Toolbar toolbar;
    //Avatar user
    public static Avatar mAvatar;
    private static LatLng mLocation;

    //Map Viewer & gestire fragment della mappa
    private static MapViewer mMapViewer;
    public static FragmentManager fragmentManager;


    //Pin Obiettivo
    private static Pin mPinTarget;
    private static Context context;
    public static GamePhase gamePhase=GamePhase.PIN_CHOICE;


    //Elementi interfaccia
    private ListView mDrawerList;
    private DrawerLayout mDrawer;
    private CustomActionBarDrawerToggle mDrawerToggle;
    private String[] menuItems;
    public static Dialog dialogIndizi;
    public static TextView Suggeritore;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_map);


        //getActionBar().setDisplayHomeAsUpEnabled(true);
        //getActionBar().setHomeButtonEnabled(true);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        // set a custom shadow that overlays the main content when the drawer
        // opens
        mDrawer.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        _initMenu();
        mDrawerToggle = new CustomActionBarDrawerToggle(this, mDrawer);
        mDrawer.setDrawerListener(mDrawerToggle);


        /*

        // Set a toolbar to replace the action bar.
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        */
        MapsActivity.Suggeritore=(TextView) findViewById(R.id.suggeritore);
        // initialising the object of the FragmentManager. Here I'm passing getSupportFragmentManager().
        // You can pass getFragmentManager() if you are coding for Android 3.0 or above.
        fragmentManager = getSupportFragmentManager();

        context = getApplicationContext();
        mMapViewer = new MapViewer();
        mAvatar = new Avatar();

        //mMarkers = mParser.parse();
        //setUpMapIfNeeded();
        mMapViewer.setUpMapIfNeeded();
        Utility.avatarMarker= mMapViewer.getmMap().addMarker(mAvatar.getMarker());
        mMapViewer.moveCameraTo(mAvatar.getLatLng(), 30);

        Utility.ZonaRioneMonti.draw();
        Utility.ZonaSanLorenzo.draw();


        ImageButton bottoneIndizi=(ImageButton) findViewById(R.id.bottoneIndizi);
        bottoneIndizi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inizializzo la mia dialog
                MapsActivity.dialogIndizi = new Dialog(MapsActivity.this);

                // Evito la presenza della barra del titolo nella mia dialog
                MapsActivity.dialogIndizi.getWindow();
                MapsActivity.dialogIndizi.requestWindowFeature(Window.FEATURE_NO_TITLE);

                // Carico il layout della dialog al suo intenro
                MapsActivity.dialogIndizi.setContentView(R.layout.popup_indizi);

                // Nel caso fosse previsto un titolo questo sarebbe il codice da
                // utilizzare eliminando quello visto poco sopra per evitarlo
                //dialog.setTitle("Testo per il titolo");

                MapsActivity.dialogIndizi.setCancelable(true);

                // Qui potrei aggiungere eventuali altre impostazioni per la dialog
                // ...

                //Gestisco il bottone di chiusura della dialog (quello in alto a destra)
                Button btnOk = (Button) MapsActivity.dialogIndizi.findViewById(R.id.popupIndiziBtnOk);
                btnOk.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        MapsActivity.dialogIndizi.dismiss();
                    }
                });
                // Faccio comparire la dialog
                MapsActivity.dialogIndizi.show();
            }
        });


        startGame();
    }

    private void _initMenu() {
        NsMenuAdapter mAdapter = new NsMenuAdapter(this);

        // Add Header
        mAdapter.addHeader(R.string.ns_menu_main_header);

        // Add first block

        menuItems = getResources().getStringArray(
                R.array.ns_menu_items);
        String[] menuItemsIcon = getResources().getStringArray(
                R.array.ns_menu_items_icon);

        int res = 0;
        for (String item : menuItems) {

            int id_title = getResources().getIdentifier(item, "string",
                    this.getPackageName());
            int id_icon = getResources().getIdentifier(menuItemsIcon[res],
                    "drawable", this.getPackageName());

            NsMenuItemModel mItem = new NsMenuItemModel(id_title, id_icon);
            if (res == 1) mItem.counter = 12; //it is just an example...
            if (res == 3) mItem.counter = 3; //it is just an example...
            mAdapter.addItem(mItem);
            res++;
        }

        mAdapter.addHeader(R.string.ns_menu_main_header2);

        this.mDrawerList = (ListView) findViewById(R.id.drawer);
        if (this.mDrawerList != null)
            this.mDrawerList.setAdapter(mAdapter);

        this.mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawer.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_save).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
		/*
		 * The action bar home/up should open or close the drawer.
		 * ActionBarDrawerToggle will take care of this.
		 */
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle your other action bar items...
        return super.onOptionsItemSelected(item);
    }

    private class CustomActionBarDrawerToggle extends ActionBarDrawerToggle {

        public CustomActionBarDrawerToggle(Activity mActivity,DrawerLayout mDrawerLayout){

            super(
                    mActivity,
                    mDrawerLayout,
                    R.string.ns_menu_open,
                    R.string.ns_menu_close);
            //                    R.drawable.ic_drawer,

        }

        @Override
        public void onDrawerClosed(View view) {
            //getActionBar().setTitle(getString(R.string.ns_menu_close));
            invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            //getActionBar().setTitle(getString(R.string.ns_menu_open)); Non funziona? problemi con l'interazione con le risorse?
            //getActionBar().setTitle("Navigation Drawer Open Menu"); //aggiunta manualmente da me

            invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // Highlight the selected item, update the title, and close the drawer
            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            String text= "menu click... should be implemented";
            Toast.makeText(MapsActivity.this, text, Toast.LENGTH_LONG).show();
            //You should reset item counter
            mDrawer.closeDrawer(mDrawerList);

        }

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
        Suggeritore.setText(R.string.scegliPinPartenza);
        //for (final Pin p: Utility.ZonaSanLorenzo.getPins_CurrentZone()){
        mMapViewer.getmMap().setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(MapsActivity.this, marker.getId(), Toast.LENGTH_LONG).show();
                int markerId = ((int) marker.getId().toString().charAt(1)) - 49;
                if (gamePhase==GamePhase.PIN_CHOICE) {
                    (Utility.ZonaSanLorenzo.getPins_CurrentZone())[markerId].setObbiettivo();
                    MapsActivity.mPinTarget = (Utility.ZonaSanLorenzo.getPins_CurrentZone())[markerId];
                    Utility.markers.get(markerId).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                    //Utility.markers.get(markerId);
                    Toast.makeText(MapsActivity.this, "You have selected the Pin with id: " + (char) (markerId + 48), Toast.LENGTH_LONG).show();
                    Suggeritore.setText(R.string.arrivaAlPin);
                    gamePhase=GamePhase.PIN_DISCOVER;
                    setupPopupIndizi();
                }
                else if (gamePhase==GamePhase.PIN_DISCOVER){
                    if (MapsActivity.mPinTarget != (Utility.ZonaSanLorenzo.getPins_CurrentZone())[markerId]) {
                        //TODO: Immagine dialogo per confermare il cambio Pin Obiettivo: per ora non lo implementiamo
                        Toast.makeText(MapsActivity.this, "You have selected the Pin with id: " +
                                (char) (markerId + 48) + " but the target Pin was already selected", Toast.LENGTH_LONG).show();
                    }
                    else{ //Pin Target == Pin premuto:
                        if (MapsActivity.mPinTarget.isIlluminato()){ //Sei vicino?

                        }
                    }
                }
                //Toast.makeText(MapsActivity.this, "", Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }


    public void setupPopupIndizi(){
        MapsActivity.dialogIndizi= new Dialog(MapsActivity.this);

        // Evito la presenza della barra del titolo nella mia dialog
        MapsActivity.dialogIndizi.getWindow();
        MapsActivity.dialogIndizi.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Carico il layout della dialog al suo intenro
        MapsActivity.dialogIndizi.setContentView(R.layout.popup_indizi);

        // Nel caso fosse previsto un titolo questo sarebbe il codice da
        // utilizzare eliminando quello visto poco sopra per evitarlo
        //dialog.setTitle("Testo per il titolo");

        MapsActivity.dialogIndizi.setCancelable(true);

        // Qui potrei aggiungere eventuali altre impostazioni per la dialog
        // ...

        //Gestisco il bottone di chiusura della dialog (quello in alto a destra)
        Button btnOk = (Button) MapsActivity.dialogIndizi.findViewById(R.id.popupIndiziBtnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MapsActivity.dialogIndizi.dismiss();
            }
        });
        // Faccio comparire la dialog
        MapsActivity.dialogIndizi.show();
    }

}