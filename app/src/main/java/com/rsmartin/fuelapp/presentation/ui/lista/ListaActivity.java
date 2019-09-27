package com.rsmartin.fuelapp.presentation.ui.lista;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.rsmartin.fuelapp.IExtras;
import com.rsmartin.fuelapp.R;
import com.rsmartin.fuelapp.domain.model.DatosGasolinera;
import com.rsmartin.fuelapp.domain.model.ListaDatosGasolineras;
import com.rsmartin.fuelapp.presentation.internal.android.SharedPref;
import com.rsmartin.fuelapp.presentation.ui.AbstractActivity;
import com.rsmartin.fuelapp.utils.Utils;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaActivity extends AbstractActivity {

    @BindView(R.id.toolbar_lista)
    Toolbar toolbar;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private ListaDatosGasolineras listaDatosGasolineras;
    private RecyclerView.LayoutManager layoutManager;
    private double currentLat;
    private double currentLon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        ButterKnife.bind(this);

        String titleScreen = getString(R.string.app_name);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            listaDatosGasolineras = (ListaDatosGasolineras) extras.getSerializable(IExtras.EXTRAS_LISTA_GAS);
            currentLat = extras.getDouble(IExtras.CURRENT_LAT);
            currentLon = extras.getDouble(IExtras.CURRENT_LONG);
            titleScreen = extras.getString(IExtras.TITLE_LISTA_ACTIVITY);
        }

        initToolbar(titleScreen);
        initAdapter();

    }

    private void initAdapter() {
        Collections.sort(listaDatosGasolineras.getDatosGasolineraList());
        ListAdapter listAdapter = new ListAdapter(ListaActivity.this,
                listaDatosGasolineras.getDatosGasolineraList(), currentLat, currentLon);
        recyclerView.setAdapter(listAdapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(ListaActivity.this, LinearLayoutManager.VERTICAL));

        layoutManager = new LinearLayoutManager(ListaActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        listAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = recyclerView.getChildAdapterPosition(view);
                DatosGasolinera datosGasolinera = listAdapter.getItem(pos);
                SharedPref.getInstance().saveStringPreferences("savedLat", datosGasolinera.getLat());
                SharedPref.getInstance().saveStringPreferences("savedLon", datosGasolinera.getLon());
                navigator.navigateToMaps(ListaActivity.this);
            }
        });
    }

    private void initToolbar(String titleScreen) {
//        setSupportActionBar(toolbar);
//        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(titleScreen);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        navigator.navigateToMaps(this);
    }


}
