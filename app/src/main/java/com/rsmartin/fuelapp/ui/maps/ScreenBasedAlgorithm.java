package com.rsmartin.fuelapp.ui.maps;


import com.google.android.gms.maps.model.CameraPosition;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.algo.Algorithm;

public interface ScreenBasedAlgorithm<T extends ClusterItem> extends Algorithm<T> {

    boolean shouldReclusterOnMapMovement();

    void onCameraChange(CameraPosition position);
}
