package com.fruitw2.hispano_mx.fruitworld2.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.fruitw2.hispano_mx.fruitworld2.Adapters.FrutaAdapter;
import com.fruitw2.hispano_mx.fruitworld2.Model.Fruta;
import com.fruitw2.hispano_mx.fruitworld2.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Fruta> list_frutas;
    private RecyclerView recycler;
    private RecyclerView.LayoutManager layoutManager;
    private FrutaAdapter adapterFruta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_frutas = getListaFrutasDefault();
        recycler = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);
        recycler.setItemAnimator(new DefaultItemAnimator());

        adapterFruta = new FrutaAdapter(list_frutas, R.layout.fruit_item, this, new FrutaAdapter.OnMyItemClickListener() {
            @Override
            public void onItemJCBClick(Fruta fruta, int position) {
                fruta.addCantidad();
                adapterFruta.notifyItemChanged(position);
            }
        });
        registerForContextMenu(recycler);

        recycler.setAdapter(adapterFruta);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_name:
                Log.i("MainActivity", "onOptionsItemSelected>>>nueva fruta");
                list_frutas.add(0,new Fruta("Nueva Fruta","Nueva",0,R.mipmap.ic_manzana, R.drawable.apple_ic));
                adapterFruta.notifyItemInserted(0);
                layoutManager.scrollToPosition(0);
                break;
            default:
                return super.onOptionsItemSelected(item);

        }
        return super.onOptionsItemSelected(item);
    }

    public List<Fruta> getListaFrutasDefault() {
        return new ArrayList<Fruta>() {{
            add(new Fruta("Manzana","Fruta antioxidante", 1,R.mipmap.ic_manzana, R.drawable.apple_ic));
            add(new Fruta("Platano","Fruta rica en potasio",2,R.mipmap.ic_platano, R.drawable.banana_ic));
            add(new Fruta("Cereza","Fruta antioxidante",3,R.mipmap.ic_cereza,R.drawable.cherry_ic));
            add(new Fruta("Naranja","Fruta con vitamina C",4,R.mipmap.ic_naranja, R.drawable.orange_ic));
        }};
    }
}
