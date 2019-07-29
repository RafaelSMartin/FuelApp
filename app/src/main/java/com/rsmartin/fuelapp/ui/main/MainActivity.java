package com.rsmartin.fuelapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.rsmartin.fuelapp.R;
import com.rsmartin.fuelapp.ui.maps.MapsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToolbar();
        initFloatingButton();
        initDrawer();

        //List<ListaEESSPrecioWraper> auxList = AppDB.getInstance(getApplicationContext()).listaEESSPrecioWraperDAO().findAllListaPrecioWraper();


//        TextView tv = findViewById(R.id.textView_hello);
//        StringBuilder auxString = new StringBuilder("cargando");
//
//        for(ListaEESSPrecioWraper aux : auxList) {
//            Log.d("DATOSDB", aux.getAddress() + "/" + aux.getLat() + "/"+ aux.getLon() + "\n");
//            auxString.append(aux.toString());
//        }
//        tv.setText(auxString.toString());

        startActivity(new Intent(this, MapsActivity.class));

    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
    }

    private void initFloatingButton(){
        fab.setOnClickListener(
                view -> Snackbar
                        .make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show());
    }

    private void initDrawer(){
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_home:
                // Handle the camera action
                break;
            case R.id.nav_gallery:

                break;
            case R.id.nav_slideshow:

                break;
            case R.id.nav_tools:

                break;
            case R.id.nav_share:

                break;
            case R.id.nav_send:

                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
