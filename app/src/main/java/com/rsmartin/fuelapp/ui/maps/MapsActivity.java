package com.rsmartin.fuelapp.ui.maps;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rsmartin.fuelapp.R;
import com.rsmartin.fuelapp.Utils.Utils;
import com.rsmartin.fuelapp.db.database.AppDB;
import com.rsmartin.fuelapp.db.entity.ListaEESSPrecioWraper;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<ListaEESSPrecioWraper> auxList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        auxList = AppDB.getInstance(getApplicationContext()).listaEESSPrecioWraperDAO().findAllListaPrecioWraper();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        for (ListaEESSPrecioWraper item : auxList) {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Utils.replaceComaToDot(item.getLat()), Utils.replaceComaToDot(item.getLon())))
                    .title(item.getRotulo()));
        }
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
