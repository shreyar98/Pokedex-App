package com.demo.mdb.pokedex;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.demo.mdb.pokedex.Pokedex;
import com.demo.mdb.pokedex.R;

import java.util.ArrayList;
import java.util.List;

import static com.demo.mdb.pokedex.R.id.pokemonImageView;


public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.CustomViewHolder> {

    private Context context;
    public ArrayList<Pokedex.Pokemon> pokemonArrayList;

    public ListViewAdapter(Context context, ArrayList<Pokedex.Pokemon> pokemon) {
        this.context = context;
        pokemonArrayList = pokemon;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        This "inflates" the views, using the layout R.layout.row_view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Pokedex.Pokemon pokemon = pokemonArrayList.get(position);

        holder.pokemonTextView.setText(pokemon.name);
        Glide.with(this.context)
                .load("http://img.pokemondb.net/artwork/" + (pokemon.name).toLowerCase().replace(" ", "").replace("'","").replace("é","e") + ".jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.pokemonImageView);
    }

    @Override
    public int getItemCount() {
        return pokemonArrayList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView pokemonTextView;
        ImageView pokemonImageView;

        public CustomViewHolder (View view) {
            super(view);
            this.pokemonTextView = (TextView) view.findViewById(R.id.pokemonTextView);
            this.pokemonImageView = (ImageView) view.findViewById(R.id.pokemonImageView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Get adapter position is getting the number of the row that was clicked,
                    starting at 0
                    */
                    Pokedex.Pokemon pokemon = pokemonArrayList.get(getAdapterPosition());
                    //Toast.makeText(context, pokemon.name, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(v.getContext(), ScrollingActivity.class);
                    intent.putExtra("Pokemon Index", getAdapterPosition());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}