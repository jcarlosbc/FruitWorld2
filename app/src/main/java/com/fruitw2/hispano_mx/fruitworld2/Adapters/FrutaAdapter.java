package com.fruitw2.hispano_mx.fruitworld2.Adapters;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.fruitw2.hispano_mx.fruitworld2.Model.Fruta;
import com.fruitw2.hispano_mx.fruitworld2.R;

import java.util.List;

/**
 * Created by jcblas on 2/15/2017.
 */

public class FrutaAdapter extends RecyclerView.Adapter<FrutaAdapter.ViewHolder> {
    private List<Fruta> lista_frutas;
    private int layout;
    private Activity activity;
    private OnMyItemClickListener mListener;

    public FrutaAdapter(List<Fruta> lista_frutas, int layout, Activity activity,FrutaAdapter.OnMyItemClickListener listener) {
        this.lista_frutas = lista_frutas;
        this.layout = layout;
        this.activity = activity;
        this.mListener = listener;
    }

    /*Metodos RecyclerView.Adapter*/
    @Override
    public FrutaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("FrutaAdapter", "FrutaAdapter.OncreateViewHolder>>se asigna clickListener" );
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener,
            MenuItem.OnMenuItemClickListener{
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
            itemView.setOnCreateContextMenuListener(this);

        }

        public void bind(final Fruta fruta) {
            img_item_layout.setImageResource(fruta.getImagen());
            img_item_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemJCBClick(fruta,getAdapterPosition());
                }
            });
            txt_name_latout.setText(fruta.getNombre());
            txt_desc_layout.setText(fruta.getDescripcion());
            txt_count_layout.setText(Integer.toString(fruta.getContador()));
            if(fruta.getContador()==10)
                txt_count_layout.setTextColor(Color.RED);
            else
                txt_count_layout.setTextColor(Color.BLACK);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            /*Esta es una opcion un tanto manual ya que se agregan los items uno por uno con el id definido en el context_menu_furta.xml
            menu.setHeaderTitle(lista_frutas.get(getAdapterPosition()).getNombre());
            menu.setHeaderIcon(lista_frutas.get(getAdapterPosition()).getIcono());
            menu.add(Menu.NONE,R.id.delete_item,Menu.NONE,"Delete").setOnMenuItemClickListener(this);
            menu.add(Menu.NONE,R.id.reset_item,Menu.NONE,"Reset Quantity").setOnMenuItemClickListener(this);*/

            /*
            De esta forma se infla el xml y se obtienen todos los menu_items para agregarles los listeners
             */
            menu.setHeaderTitle(lista_frutas.get(getAdapterPosition()).getNombre());
            menu.setHeaderIcon(lista_frutas.get(getAdapterPosition()).getIcono());
            activity.getMenuInflater().inflate(R.menu.context_menu_furta,menu);
            for (int i = 0;i<menu.size();i++){
                menu.getItem(i).setOnMenuItemClickListener(this);
            }
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.delete_item:
                    lista_frutas.remove(getAdapterPosition());
                    //notifyDataSetChanged();
                    notifyItemRemoved(getAdapterPosition());
                    break;
                case R.id.reset_item:
                    lista_frutas.get(getAdapterPosition()).resetContador();
                    //notifyDataSetChanged();
                    notifyItemChanged(getAdapterPosition());
                    break;
                default:
                    return  false;
            }

            return true;
        }
    }

    public interface OnMyItemClickListener {
        void onItemJCBClick(Fruta fruta, int position);
    }
}
