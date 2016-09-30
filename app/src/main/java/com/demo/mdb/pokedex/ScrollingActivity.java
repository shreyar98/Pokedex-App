package com.demo.mdb.pokedex;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class ScrollingActivity extends AppCompatActivity {
    Pokedex pokedex = new Pokedex();
    final ArrayList<Pokedex.Pokemon> pokemonArrayList = pokedex.getPokemon();
    int pokemonIndex;
    Pokedex.Pokemon pokemon;

    Context context;
    Intent intent;
    Toolbar toolbar;
    CollapsingToolbarLayout toolbarLayout;
    ImageView imageView;
    TextView textView;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        intent = getIntent();
        pokemonIndex = intent.getIntExtra("Pokemon Index", 0);
        pokemon = pokemonArrayList.get(pokemonIndex);
        String name = pokemon.name;
        String number = pokemon.number;
        String attack = pokemon.attack;
        String defense = pokemon.defense;
        String hp = pokemon.hp;
        String species = pokemon.species;

        setSupportActionBar(toolbar);
        toolbarLayout.setTitle(name);

        Glide.with(this)
                .load("http://img.pokemondb.net/artwork/" + (name).toLowerCase().replace(" ", "").replace("'","").replace("é","e") + ".jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEARCH);
                intent.putExtra(SearchManager.QUERY, pokemon.name);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        textView.setText(
                "Number: " + number +
                "\nAttack: " + attack +
                "\nDefense: " + defense +
                "\nHP: " + hp +
                "\nSpecies: " + species);
    }
}
