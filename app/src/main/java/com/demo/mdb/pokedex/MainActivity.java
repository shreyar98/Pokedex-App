package com.demo.mdb.pokedex;

import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.content.Intent;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button button;
    Switch lowSwitch;
    Switch medSwitch;
    Switch highSwitch;
    Boolean low = true;
    Boolean med = true;
    Boolean high = true;
    Pokedex pokedex = new Pokedex();
    Boolean isGridView = false;
    final ArrayList<Pokedex.Pokemon> pokemonArrayList = pokedex.getPokemon();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lowSwitch = (Switch) findViewById(R.id.lowSwitch);
        medSwitch = (Switch) findViewById(R.id.medSwitch);
        highSwitch = (Switch) findViewById(R.id.highSwitch);
        button = (Button) findViewById(R.id.button);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("isGridView", isGridView);
                startActivity(intent);
            }
        });

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final ListViewAdapter listPokemonAdapter = new ListViewAdapter(getApplicationContext(), pokemonArrayList);
        final GridViewAdapter gridPokemonAdapter = new GridViewAdapter(getApplicationContext(), pokemonArrayList);
        recyclerView.setAdapter(listPokemonAdapter);

        lowSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                low = b;
                listPokemonAdapter.pokemonArrayList = updatedArrayList();
                listPokemonAdapter.notifyDataSetChanged();
                gridPokemonAdapter.pokemonArrayList = updatedArrayList();
                gridPokemonAdapter.notifyDataSetChanged();
            }
        });

        medSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                med = b;
                listPokemonAdapter.pokemonArrayList = updatedArrayList();
                listPokemonAdapter.notifyDataSetChanged();
                gridPokemonAdapter.pokemonArrayList = updatedArrayList();
                gridPokemonAdapter.notifyDataSetChanged();
            }
        });

        highSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                high = b;
                listPokemonAdapter.pokemonArrayList = updatedArrayList();
                listPokemonAdapter.notifyDataSetChanged();
                gridPokemonAdapter.pokemonArrayList = updatedArrayList();
                gridPokemonAdapter.notifyDataSetChanged();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                isGridView = !isGridView;
                //ArrayList<Pokedex.Pokemon> list = listPokemonAdapter.pokemonArrayList;
                if (isGridView) {
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                    //pokemonAdapter = new GridViewAdapter(getApplicationContext(), list);
                    recyclerView.setAdapter(gridPokemonAdapter);
                } else {
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    //PokemonAdapter = new ListViewAdapter(getApplicationContext(), list);
                    recyclerView.setAdapter(listPokemonAdapter);
                }
            }
        });
    }

    public ArrayList<Pokedex.Pokemon> updatedArrayList()
    {
        ArrayList<Pokedex.Pokemon> updatedPokemonArrayList = new ArrayList<Pokedex.Pokemon>(pokemonArrayList);
        ArrayList<Pokedex.Pokemon> toRemove = new ArrayList<Pokedex.Pokemon>();
        for(Pokedex.Pokemon pokemon: updatedPokemonArrayList)
        {
            int pokemonHP = Integer.parseInt(pokemon.hp);
            if (!low && pokemonHP < 50)
            {
                toRemove.add(pokemon);
            }
            else if (!med && pokemonHP >= 50 && pokemonHP <= 100)
            {
                toRemove.add(pokemon);
            }
            else if (!high && pokemonHP > 100)
            {
                toRemove.add(pokemon);
            }
        }
        updatedPokemonArrayList.removeAll(toRemove);
        return updatedPokemonArrayList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
