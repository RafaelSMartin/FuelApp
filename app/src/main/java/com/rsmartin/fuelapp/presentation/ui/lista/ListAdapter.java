package com.rsmartin.fuelapp.presentation.ui.lista;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rsmartin.fuelapp.R;
import com.rsmartin.fuelapp.domain.model.DatosGasolinera;
import com.rsmartin.fuelapp.utils.Utils;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private View.OnClickListener onClickListener;
    private List<DatosGasolinera> model;
    private double latitude;
    private double longitude;

    public ListAdapter(Context context, List<DatosGasolinera> model, double latitude, double longitude) {
        this.context = context;
        this.model = model;
        this.latitude = latitude;
        this.longitude = longitude;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vItem = inflater.inflate(R.layout.list_item, parent, false);
        vItem.setOnClickListener(onClickListener);
        return new VHItem(vItem);
    }

    private DatosGasolinera getItem(int position) {
        return model.get(position);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class VHItem extends RecyclerView.ViewHolder {

        @BindView(R.id.logo)
        ImageView logo;

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.address)
        TextView address;

        @BindView(R.id.price)
        TextView price;

        @BindView(R.id.distance)
        TextView distance;

        public VHItem(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int pos) {
        VHItem vhItem = ((VHItem) holder);
        DatosGasolinera item = model.get(pos);

        vhItem.logo.setImageDrawable(mapIconOils(context, item.getRotulo().toString().trim()));
        vhItem.name.setText(item.getRotulo());
        vhItem.address.setText(item.getAddress());
        vhItem.price.setText(
                "Gas95 -> " + item.getPrecioGasolina95Proteccion() + " € " + "\n" +
                        "Gas98 -> " + item.getPrecioGasolina98() + " € " + "\n" +
                        "Gasoleo A -> " + item.getPrecioGasoleoA() + " € " + "\n" +
                        "Gasoleo B -> " + item.getPrecioGasoleoB() + " € " + "\n" +
                        "Nuevo Gasoleo A -> " + item.getPrecioNuevoGasoleoA() + " € " + "\n" +
                        "Biodiesel -> " + item.getPrecioBiodiesel() + " € " + "\n"
        );

        double distance = Utils.distance(
                Utils.replaceComaToDot(item.getLat()),
                Utils.replaceComaToDot(item.getLon()),
                latitude, longitude);
        String distanceFormatted = new DecimalFormat("##.##").format(distance);
        vhItem.distance.setText(distanceFormatted + " Km.");

    }

    private Drawable mapIconOils(Context context, String name) {
        Drawable resource = null;

        if (name.contains("ALCAMPO")) {
            resource = context.getResources().getDrawable(R.drawable.marker_alcampo);
        } else if (name.contains("AGLA")) {
            resource = context.getResources().getDrawable(R.drawable.marker_agla);
        } else if (name.contains("ANDAMUR")) {
            resource = context.getResources().getDrawable(R.drawable.marker_andamur);
        } else if (name.contains("AVIA")) {
            resource = context.getResources().getDrawable(R.drawable.marker_avia);
        } else if (name.contains("BALLENOIL")) {
            resource = context.getResources().getDrawable(R.drawable.marker_ballenoil);
        } else if (name.contains("BP")) {
            resource = context.getResources().getDrawable(R.drawable.marker_bp);
        } else if (name.contains("CAMPSA")) {
            resource = context.getResources().getDrawable(R.drawable.marker_campsa);
        } else if (name.contains("CARREFOUR")) {
            resource = context.getResources().getDrawable(R.drawable.marker_carrefour);
        } else if (name.contains("CEPSA")) {
            resource = context.getResources().getDrawable(R.drawable.marker_cepsa);
        } else if (name.contains("EASYGAS")) {
            resource = context.getResources().getDrawable(R.drawable.marker_easygas);
        } else if (name.contains("EROSKI")) {
            resource = context.getResources().getDrawable(R.drawable.marker_eroski);
        } else if (name.contains("EUROCAM")) {
            resource = context.getResources().getDrawable(R.drawable.marker_eurocam);
        } else if (name.contains("GALP")) {
            resource = context.getResources().getDrawable(R.drawable.marker_galp);
        } else if (name.contains("PETRONOR")) {
            resource = context.getResources().getDrawable(R.drawable.marker_petronor);
        } else if (name.contains("PLENOIL")) {
            resource = context.getResources().getDrawable(R.drawable.marker_plenoil);
        } else if (name.contains("REPSOL")) {
            resource = context.getResources().getDrawable(R.drawable.marker_repsol);
        } else if (name.contains("SARAS")) {
            resource = context.getResources().getDrawable(R.drawable.marker_saras);
        } else if (name.contains("SHELL")) {
            resource = context.getResources().getDrawable(R.drawable.marker_shell);
        } else {
            resource = context.getResources().getDrawable(R.mipmap.ic_launcher_fuelapp);
        }

        return resource;
    }

}
