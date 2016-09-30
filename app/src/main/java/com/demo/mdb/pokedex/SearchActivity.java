package com.demo.mdb.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    TextView search;
    Pokedex pokedex = new Pokedex();
    RecyclerView searchRecyclerView;
    final ArrayList<Pokedex.Pokemon> pokemonArrayList = pokedex.getPokemon();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
        Boolean isGridView = intent.getExtras().getBoolean("isGridView");

        search = (TextView) findViewById(R.id.editText);

        searchRecyclerView = (RecyclerView) findViewById(R.id.searchRecyclerView);
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        final GridViewAdapter gridPokemonAdapter = new GridViewAdapter(getApplicationContext(), pokemonArrayList);
        final ListViewAdapter listPokemonAdapter = new ListViewAdapter(getApplicationContext(), pokemonArrayList);
        if (isGridView)
        {
            searchRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
            searchRecyclerView.setAdapter(gridPokemonAdapter);
        }
        else
        {
            searchRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            searchRecyclerView.setAdapter(listPokemonAdapter);
        }


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String query = search.getText().toString();
               if (query.length() <= 0)
               {
                   listPokemonAdapter.pokemonArrayList = pokemonArrayList;
                   gridPokemonAdapter.pokemonArrayList = pokemonArrayList;
                   listPokemonAdapter.notifyDataSetChanged();
               }
                else
               {
                   listPokemonAdapter.pokemonArrayList = updatedArrayList(query);
                   gridPokemonAdapter.pokemonArrayList = updatedArrayList(query);
                   listPokemonAdapter.notifyDataSetChanged();
                   gridPokemonAdapter.notifyDataSetChanged();
               }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public ArrayList<Pokedex.Pokemon> updatedArrayList(String query)
    {
        ArrayList<Pokedex.Pokemon> updatedPokemonArrayList = new ArrayList<Pokedex.Pokemon>(pokemonArrayList);
        ArrayList<Pokedex.Pokemon> toRemove = new ArrayList<Pokedex.Pokemon>();
        for(Pokedex.Pokemon pokemon: updatedPokemonArrayList)
        {
            if (!pokemon.name.toLowerCase().replace(" ", "").replace("'","").replace("é","e").contains(query.toLowerCase()))
            {
                toRemove.add(pokemon);
            }
        }
        updatedPokemonArrayList.removeAll(toRemove);
        return updatedPokemonArrayList;
    }


}
