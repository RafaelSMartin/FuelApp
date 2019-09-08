package com.rsmartin.fuelapp.presentation.ui.map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.algo.NonHierarchicalDistanceBasedAlgorithm;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.google.maps.android.ui.SquareTextView;
import com.rsmartin.fuelapp.App;
import com.rsmartin.fuelapp.IExtras;
import com.rsmartin.fuelapp.R;
import com.rsmartin.fuelapp.domain.model.DatosGasolinera;
import com.rsmartin.fuelapp.domain.model.ListaDatosGasolineras;
import com.rsmartin.fuelapp.presentation.internal.android.SharedPref;
import com.rsmartin.fuelapp.presentation.internal.room.database.AppDB;
import com.rsmartin.fuelapp.presentation.ui.AbstractFragmentActivity;
import com.rsmartin.fuelapp.presentation.ui.customdetail.CustomDetailFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapsActivity extends AbstractFragmentActivity implements MapsPresenter.View,
        NavigationView.OnNavigationItemSelectedListener, GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback, GoogleMap.OnMapClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.custom_detail)
    FrameLayout customDetail;

    @Inject
    MapsPresenter mapsPresenter;

    private GoogleMap mMap;
    private ClusterManager<DatosGasolinera> mClusterManager;
    private List<DatosGasolinera> listOils = new ArrayList<>();
    private Context context;
    private FirebaseUser user;
    private boolean isAnimationFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);

        getApplicationComponent().inject(this);
        mapsPresenter.setView(this);

        context = getApplicationContext();

        if (getIntent().getExtras() != null) {
            ListaDatosGasolineras listaDatosGasolineras = (ListaDatosGasolineras) getIntent().getSerializableExtra(IExtras.ARGS_LIST_OILS_SHORT);
            if (listaDatosGasolineras != null) {
                listOils = listaDatosGasolineras.getDatosGasolineraList();
            }
        }

        initToolbar();
        initDrawer();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    private void initToolbar() {
//        setSupportActionBar(toolbar);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
    }

    private void initDrawer() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        View navigationHeaderView = navigationView.getHeaderView(0);
        ImageView imageHeader = navigationHeaderView.findViewById(R.id.image_header);
        TextView nameHeader = navigationHeaderView.findViewById(R.id.name_header);
        TextView emailHeader = navigationHeaderView.findViewById(R.id.email_header);

        imageHeader.setImageDrawable(getDrawable(R.mipmap.ic_launcher_round));
        nameHeader.setText(SharedPref.getInstance().getStringPreferences(IExtras.USER_NAME));
        emailHeader.setText(SharedPref.getInstance().getStringPreferences(IExtras.USER_EMAIL));

        TextView version = navigationView.findViewById(R.id.version);
        version.setText(new StringBuilder()
                .append(getResources().getString(R.string.version))
                .append(" ")
                .append(getVersionName())
                .toString());

        Button login = navigationView.findViewById(R.id.login);

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            login.setText("LogOut");
        } else {
            login.setText("LogIn");
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user != null) { //Tengo login
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    mAuth.signOut();
                    user = null;
                    mapsPresenter.deleteUserInfo();
                    login.setText("LogIn");
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START);
                    }
                    imageHeader.setImageDrawable(getDrawable(R.mipmap.ic_launcher_round));
                    nameHeader.setText("FuelApp");
                    emailHeader.setText("La mejor App de gasolineras");

                    Toast.makeText(getApplicationContext(), "LogOut exitoso", Toast.LENGTH_SHORT).show();
                } else { // No tengo login
                    navigator.navigateToLogin(getApplicationContext());
                    login.setText("LogOut");
                }
            }
        });

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_map:
                break;
            case R.id.nav_gallery:
                break;
            case R.id.nav_favoritos:
                break;
            case R.id.nav_preferencias:
                break;
            case R.id.nav_share:
                mapsPresenter.shareApp(context);
                break;
            case R.id.nav_send:
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().isCompassEnabled();
        mMap.getUiSettings().isZoomControlsEnabled();
        mMap.setMyLocationEnabled(true);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapClickListener(this);

//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        mClusterManager = new ClusterManager<DatosGasolinera>(this, getMap());

        CustomMarkerClustering customMarkerClustering = new CustomMarkerClustering(this, mMap, mClusterManager);
        customMarkerClustering.setMinClusterSize(1);

        mClusterManager.setRenderer(customMarkerClustering);
        mClusterManager.setAlgorithm(new NonHierarchicalDistanceBasedAlgorithm<DatosGasolinera>());
        mMap.setOnCameraIdleListener(mClusterManager);

        drawOils(listOils);

        mMap.setOnMarkerClickListener(this);
    }

    public void drawOils(List<DatosGasolinera> auxList) {
        for (DatosGasolinera item : auxList) {
            mClusterManager.addItem(item);
            mClusterManager.cluster();
        }
    }

    public void getOilsFromRoom() {
        FindAllListaPrecioWraperTask findAllListaPrecioWraperTask = new FindAllListaPrecioWraperTask();
        findAllListaPrecioWraperTask.execute();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        isAnimationFinished = false;
        // Retrieve the data from the marker.
        // Check if a click count was set, then display the click count.
        DatosGasolinera tag = (DatosGasolinera) marker.getTag();

        if (tag != null) {
//            String municipio = tag.getMunicipio() != null ? tag.getMunicipio() : "";
//            Toast.makeText(this, marker.getTitle() + " has been clicked en " + municipio, Toast.LENGTH_SHORT).show();
            openDetail(tag);
        }

        return zoomInCluster(mMap, marker, mClusterManager);
    }

    public boolean zoomInCluster(GoogleMap mMap, Marker marker, ClusterManager<DatosGasolinera> mClusterManager) {
        if (mapsPresenter.isClusterMarker(marker, mClusterManager)) {
            float currentZoom = mMap.getCameraPosition().zoom;
            int increment = 3;
            if (currentZoom + increment <= mMap.getMaxZoomLevel()) {
                currentZoom = currentZoom + increment;
            } else {
                currentZoom = mMap.getMinZoomLevel();
            }
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), currentZoom),
                    new GoogleMap.CancelableCallback() {
                        @Override
                        public void onFinish() {
                            isAnimationFinished = true;
                        }

                        @Override
                        public void onCancel() {
                            isAnimationFinished = true;
                        }
                    });
            return !isAnimationFinished;
        }
        return false;
    }

    private void openDetail(DatosGasolinera datosGasolinera) {
        LatLng currentLatLong = mapsPresenter.getMyCurrentLocation(context);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.custom_detail, CustomDetailFragment.newInstance(datosGasolinera,
                        currentLatLong.latitude, currentLatLong.longitude))
                .commit();

        mapsPresenter.toggle(customDetail, true, R.id.custom_detail);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if (customDetail.getVisibility() == View.VISIBLE) {
            mapsPresenter.toggle(customDetail, false, R.id.custom_detail);
        }
    }

    private GoogleMap getMap() {
        return mMap;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public class CustomMarkerClustering extends DefaultClusterRenderer<DatosGasolinera> {

        private final IconGenerator mIconGenerator;
        private final float mDensity;
        private final int[] BUCKETS = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446, 447, 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 480, 481, 482, 483, 484, 485, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 496, 497, 498, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 512, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569, 570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636, 637, 638, 639, 640, 641, 642, 643, 644, 645, 646, 647, 648, 649, 650, 651, 652, 653, 654, 655, 656, 657, 658, 659, 660, 661, 662, 663, 664, 665, 666, 667, 668, 669, 670, 671, 672, 673, 674, 675, 676, 677, 678, 679, 680, 681, 682, 683, 684, 685, 686, 687, 688, 689, 690, 691, 692, 693, 694, 695, 696, 697, 698, 699, 700, 701, 702, 703, 704, 705, 706, 707, 708, 709, 710, 711, 712, 713, 714, 715, 716, 717, 718, 719, 720, 721, 722, 723, 724, 725, 726, 727, 728, 729, 730, 731, 732, 733, 734, 735, 736, 737, 738, 739, 740, 741, 742, 743, 744, 745, 746, 747, 748, 749, 750, 751, 752, 753, 754, 755, 756, 757, 758, 759, 760, 761, 762, 763, 764, 765, 766, 767, 768, 769, 770, 771, 772, 773, 774, 775, 776, 777, 778, 779, 780, 781, 782, 783, 784, 785, 786, 787, 788, 789, 790, 791, 792, 793, 794, 795, 796, 797, 798, 799, 800, 801, 802, 803, 804, 805, 806, 807, 808, 809, 810, 811, 812, 813, 814, 815, 816, 817, 818, 819, 820, 821, 822, 823, 824, 825, 826, 827, 828, 829, 830, 831, 832, 833, 834, 835, 836, 837, 838, 839, 840, 841, 842, 843, 844, 845, 846, 847, 848, 849, 850, 851, 852, 853, 854, 855, 856, 857, 858, 859, 860, 861, 862, 863, 864, 865, 866, 867, 868, 869, 870, 871, 872, 873, 874, 875, 876, 877, 878, 879, 880, 881, 882, 883, 884, 885, 886, 887, 888, 889, 890, 891, 892, 893, 894, 895, 896, 897, 898, 899, 900, 901, 902, 903, 904, 905, 906, 907, 908, 909, 910, 911, 912, 913, 914, 915, 916, 917, 918, 919, 920, 921, 922, 923, 924, 925, 926, 927, 928, 929, 930, 931, 932, 933, 934, 935, 936, 937, 938, 939, 940, 941, 942, 943, 944, 945, 946, 947, 948, 949, 950, 951, 952, 953, 954, 955, 956, 957, 958, 959, 960, 961, 962, 963, 964, 965, 966, 967, 968, 969, 970, 971, 972, 973, 974, 975, 976, 977, 978, 979, 980, 981, 982, 983, 984, 985, 986, 987, 988, 989, 990, 991, 992, 993, 994, 995, 996, 997, 998, 999, 1000, 2000, 3000, 4000, 5000, 10000};
        private ShapeDrawable mColoredCircleBackground;
        private SparseArray<BitmapDescriptor> mIcons = new SparseArray();

        public CustomMarkerClustering(Context context, GoogleMap map,
                                      ClusterManager<DatosGasolinera> clusterManager) {
            super(context, map, clusterManager);
            this.mDensity = context.getResources().getDisplayMetrics().density;
            this.mIconGenerator = new IconGenerator(context);
            this.mIconGenerator.setContentView(this.makeSquareTextView(context));
            this.mIconGenerator.setTextAppearance(R.style.fuelapp_ClusterIcon_TextAppearance);//Color del numero dentro del cluster
            this.mIconGenerator.setBackground(this.makeClusterBackground());
        }

        private SquareTextView makeSquareTextView(Context context) {
            SquareTextView squareTextView = new SquareTextView(context);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -2);
            squareTextView.setLayoutParams(layoutParams);
            squareTextView.setId(com.google.maps.android.R.id.amu_text);
            int textPaddingDpi = (int) (6.5F * this.mDensity);
            squareTextView.setPadding(textPaddingDpi, textPaddingDpi, textPaddingDpi, textPaddingDpi);
            return squareTextView;
        }

        private LayerDrawable makeClusterBackground() {
            this.mColoredCircleBackground = new ShapeDrawable(new OvalShape());
            ShapeDrawable outline = new ShapeDrawable(new OvalShape());
            outline.getPaint().setColor(Color.parseColor("#11000000"));
            LayerDrawable background = new LayerDrawable(new Drawable[]{outline, this.mColoredCircleBackground});
            int strokeWidth = (int) (this.mDensity * 1.0F);
            background.setLayerInset(1, strokeWidth, strokeWidth, strokeWidth, strokeWidth);
            return background;
        }

        @Override
        protected void onBeforeClusterItemRendered(DatosGasolinera item, MarkerOptions markerOptions) {
            super.onBeforeClusterItemRendered(item, markerOptions);
        }

        @Override
        protected void onBeforeClusterRendered(Cluster<DatosGasolinera> cluster, MarkerOptions markerOptions) {
            super.onBeforeClusterRendered(cluster, markerOptions);
            int bucket = this.getBucket(cluster);
            BitmapDescriptor descriptor = this.mIcons.get(bucket);
            if (descriptor == null) {
                this.mColoredCircleBackground.getPaint().setColor(this.getColor(bucket));
                descriptor = BitmapDescriptorFactory.fromBitmap(this.mIconGenerator.makeIcon(this.getClusterText(bucket)));
                this.mIcons.put(bucket, descriptor);
            }
            markerOptions.icon(descriptor);
        }

        @Override
        protected void onClusterItemRendered(DatosGasolinera clusterItem, Marker marker) {
            super.onClusterItemRendered(clusterItem, marker);

            marker.setTag(clusterItem);
            //Para pintar el item al final de la agrupacion de clustered de google
//            marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_low));
            Bitmap bitmap = mapsPresenter.paintLogo(context, clusterItem.getRotulo());
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(bitmap));

        }


        @Override
        protected int getColor(int clusterSize) {
            return Color.parseColor("#0097a7"); //Color del fondo del cluster
        }

        @Override
        protected String getClusterText(int bucket) {
            return String.valueOf(bucket);
        }

        @Override
        protected int getBucket(Cluster<DatosGasolinera> cluster) {
            int size = cluster.getSize();

            if (size <= BUCKETS[0]) {
                return size;
            } else {
                for (int i = 0; i < BUCKETS.length - 1; ++i) {
                    if (size < BUCKETS[i + 1]) {
                        return BUCKETS[i];
                    }
                }

                return BUCKETS[BUCKETS.length - 1];
            }
        }
    }

    public class FindAllListaPrecioWraperTask extends AsyncTask<Void, Void, List<DatosGasolinera>> {

        @Override
        protected List<DatosGasolinera> doInBackground(Void... voids) {
            return AppDB.getInstance(App.getInstance().getApplicationContext())
                    .gasolinerasDAO().findAllPreciosGasolineras();
        }

        @Override
        protected void onPostExecute(List<DatosGasolinera> lists) {
            drawOils(lists);
        }
    }

}


