package com.rsmartin.fuelapp.presentation.ui.map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.widget.FrameLayout;

import androidx.transition.Slide;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.rsmartin.fuelapp.IExtras;
import com.rsmartin.fuelapp.domain.executor.ErrorHandler;
import com.rsmartin.fuelapp.domain.model.DatosGasolinera;
import com.rsmartin.fuelapp.presentation.internal.android.SharedPref;
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

    public void deleteUserInfo() {
        SharedPref.getInstance().removePreference(IExtras.USER_NAME);
        SharedPref.getInstance().removePreference(IExtras.USER_EMAIL);
        SharedPref.getInstance().removePreference(IExtras.USER_PHOTO_URL);
        SharedPref.getInstance().removePreference(IExtras.USER_EMAIL_VERIFIED);
        SharedPref.getInstance().removePreference(IExtras.USER_UID);
    }

    public void toggle(FrameLayout view, boolean visibility, int idTarget) {
        Transition transition = new Slide();
        transition.setDuration(300);
        transition.addTarget(idTarget);

        TransitionManager.beginDelayedTransition(view, transition);
        view.setVisibility(visibility ? android.view.View.VISIBLE : android.view.View.GONE);
    }

    public LatLng getMyCurrentLocation(Context context) {
        LocationManager locationManager = (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        @SuppressLint("MissingPermission")
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        return new LatLng(latitude, longitude);
    }

    public interface View {
        String getVersionName();
    }
}
