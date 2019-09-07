package com.rsmartin.fuelapp.presentation.ui.map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
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
import com.rsmartin.fuelapp.R;
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

    @SuppressLint("MissingPermission")
    public LatLng getMyCurrentLocation(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        return new LatLng(latitude, longitude);
    }

    public Bitmap paintLogo(Context context, String name) {
        Bitmap bitmapBase = BitmapFactory.decodeResource(
                context.getResources(), R.drawable.ic_marker);
        Bitmap bmpBase = bitmapBase.copy(Bitmap.Config.ARGB_8888, true);

        Bitmap bitmapLogo = getImageBitmap(context, name);
        if (bitmapLogo != null) {
            Bitmap bmpLogo = bitmapLogo.copy(Bitmap.Config.ARGB_8888, true);

            Canvas c = new Canvas(bmpBase);
            int width = (int) bmpLogo.getWidth();
            int height = (int) bmpLogo.getHeight();

            c.drawBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_marker),
                    0, 0, new Paint());

            int centerWidth = (bmpBase.getWidth() - width) / 2;
            int heightDelay = (bmpLogo.getHeight() / 3);
            int centerHeight = ((bmpBase.getHeight() - height) / 2) - heightDelay;

            c.drawBitmap(bitmapLogo, centerWidth, centerHeight, new Paint());
        }

        return bmpBase;
    }

    private Bitmap getImageBitmap(Context context, String name) {
        Bitmap bitmap = null;

        if (name.contains("ALCAMPO")) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.marker_alcampo);
        } else if (name.contains("ANDAMUR")) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.marker_andamur);
        } else if (name.contains("AVIA")) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.marker_avia);
        } else if (name.contains("BALLENOIL")) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.marker_ballenoil);
        } else if (name.contains("BP")) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.marker_bp);
        } else if (name.contains("CAMPSA")) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.marker_campsa);
        } else if (name.contains("CARREFOUR")) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.marker_carrefour);
        } else if (name.contains("CEPSA")) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.marker_cepsa);
        } else if (name.contains("EASYGAS")) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.marker_easygas);
        } else if (name.contains("EROSKI")) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.marker_eroski);
        } else if (name.contains("EUROCAM")) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.marker_eurocam);
        } else if (name.contains("GALP")) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.marker_galp);
        } else if (name.contains("PETRONOR")) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.marker_petronor);
        } else if (name.contains("PLENOIL")) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.marker_plenoil);
        } else if (name.contains("REPSOL")) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.marker_repsol);
        } else if (name.contains("SARAS")) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.marker_saras);
        } else if (name.contains("SHELL")) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.marker_shell);
        }

        return bitmap;
    }

    public interface View {
        String getVersionName();
    }
}
