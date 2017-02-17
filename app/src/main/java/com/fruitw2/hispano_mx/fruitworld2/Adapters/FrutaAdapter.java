package com.fruitw2.hispano_mx.fruitworld2.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fruitw2.hispano_mx.fruitworld2.Model.Fruta;
import com.fruitw2.hispano_mx.fruitworld2.R;

import java.util.List;

/**
 * Created by jcblas on 2/15/2017.
 */

public class FrutaAdapter extends RecyclerView.Adapter<FrutaAdapter.ViewHolder> implements View.OnClickListener {
    private List<Fruta> lista_frutas;
    private View.OnClickListener mListener;

    public FrutaAdapter(List<Fruta> lista_frutas) {
        this.lista_frutas = lista_frutas;
    }

    /*Metodos RecyclerView.Adapter*/
    @Override
    public FrutaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,parent,false);
        Log.i("FrutaAdapter", "FrutaAdapter.OncreateViewHolder>>se asigna clickListener" );
        itemView.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(FrutaAdapter.ViewHolder holder, int position) {
        holder.bind(lista_frutas.get(position));
    }

    @Override
    public int getItemCount() {
        return lista_frutas.size();
    }

    public void setOnJCClickListener(View.OnClickListener listener) {
        Log.i("FrutaAdapter", "FrutaAdapter.setOnJCClickListener " );
        this.mListener = listener;
    }

    /*Metodo del OnclickListener*/
    @Override
    public void onClick(View v) {
        Log.i("FrutaAdapter", "FrutaAdapter.onClick Inicia" );
        if(mListener != null)
            mListener.onClick(v);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img_item_layout;
        public TextView txt_name_latout;
        public TextView txt_desc_layout;
        public TextView txt_count_layout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.img_item_layout = (ImageView) itemView.findViewById(R.id.imageView);
            this.txt_name_latout = (TextView) itemView.findViewById(R.id.txt_name);
            this.txt_desc_layout = (TextView) itemView.findViewById(R.id.txt_description);
            this.txt_count_layout = (TextView) itemView.findViewById(R.id.txt_cantidad);
        }

        public void bind(Fruta fruta) {
            img_item_layout.setImageResource(fruta.getImagen());
            txt_name_latout.setText(fruta.getNombre());
            txt_desc_layout.setText(fruta.getDescripcion());
            txt_count_layout.setText(fruta.getContador());
        }
    }
}
