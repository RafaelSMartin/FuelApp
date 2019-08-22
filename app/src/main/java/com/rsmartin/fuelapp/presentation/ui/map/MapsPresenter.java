package com.rsmartin.fuelapp.presentation.ui.map;

import android.content.Context;
import android.content.Intent;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.rsmartin.fuelapp.domain.executor.ErrorHandler;
import com.rsmartin.fuelapp.domain.model.DatosGasolinera;
import com.rsmartin.fuelapp.presentation.ui.AbstractPresenter;

import javax.inject.Inject;

public class MapsPresenter extends AbstractPresenter<MapsPresenter.View> {

    @Inject
    public MapsPresenter(ErrorHandler errorHandler) {
        super(errorHandler);
    }

    public void shareApp(Context context) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "https://play.google.com/store/apps/details?id=" + context.getPackageName());
        sendIntent.setType("text/plain");
        sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(sendIntent);
    }

    public boolean zoomInCluster(GoogleMap mMap, Marker marker, ClusterManager<DatosGasolinera> mClusterManager) {
        if (isClusterMarker(marker, mClusterManager)) {
            float currentZoom = mMap.getCameraPosition().zoom;
            int increment = 3;
            if (currentZoom + increment <= mMap.getMaxZoomLevel()) {
                currentZoom = currentZoom + increment;
            } else {
                currentZoom = mMap.getMinZoomLevel();
            }
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), currentZoom));
            return true;
        }
        return false;
    }

    public boolean isClusterMarker(Marker marker, ClusterManager<DatosGasolinera> mClusterManager) {
        boolean isClusterMarkerBoolean = false;
        if (mClusterManager == null) {
            isClusterMarkerBoolean = false;
        } else {
            isClusterMarkerBoolean = ((DefaultClusterRenderer) mClusterManager.getRenderer()).getClusterItem(marker) == null;
        }
        return isClusterMarkerBoolean;
    }

    public Cluster<DatosGasolinera> getCluster(Marker marker, ClusterManager<DatosGasolinera> mClusterManager) {
        return ((DefaultClusterRenderer) mClusterManager.getRenderer()).getCluster(marker);
    }

    public interface View {

    }
}
