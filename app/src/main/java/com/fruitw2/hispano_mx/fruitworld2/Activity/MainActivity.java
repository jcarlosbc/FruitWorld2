package com.fruitw2.hispano_mx.fruitworld2.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
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
    private FrutaAdapter adapterFruta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_frutas = getListaFrutasDefault();

        recycler = (RecyclerView) findViewById(R.id.recycler_view);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);
        recycler.setItemAnimator(new DefaultItemAnimator());

        adapterFruta = new FrutaAdapter(list_frutas);
        adapterFruta.setOnJCClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MainActivity", "onCreate.onClick");
            }
        });

        recycler.setAdapter(adapterFruta);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add,menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        menu.setHeaderTitle(list_frutas.get(info.position).getNombre());
        menu.setHeaderIcon(list_frutas.get(info.position).getIcono());
        getMenuInflater().inflate(R.menu.context_menu_furta,menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_name:
                break;
            default:
                return super.onOptionsItemSelected(item);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.delete_item:
                /*fruits.remove(info.position);
                adapter.notifyDataSetChanged();
                adapter_grid.notifyDataSetChanged();*/
                return true;
            case R.id.reset_item:
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    public List<Fruta> getListaFrutasDefault() {
        return new ArrayList<Fruta>() {{
            add(new Fruta("Manzana","Fruta antioxidante", "1",R.drawable.apple_bg, R.drawable.apple_ic));
            add(new Fruta("Platano","Fruta rica en potasio","2",R.drawable.banana_bg, R.drawable.banana_ic));
            add(new Fruta("Cereza","Fruta antioxidante","3",R.drawable.cherry_bg,R.drawable.cherry_ic));
            add(new Fruta("Naranja","Fruta con vitamina C","4",R.drawable.orange_bg, R.drawable.orange_ic));
            /*add(new Fruta("Pera","Fruta cara","5",R.drawable.pear_bg, R.drawable.pear_ic));
            add(new Fruta("Mora","Fruta antioxidante","6",R.drawable.raspberry_bg,R.drawable.raspberry_ic));
            add(new Fruta("Fresa","Fruta afrodicica","7",R.drawable.strawberry_bg, R.drawable.strawberry_ic));*/
        }};
    }
}
