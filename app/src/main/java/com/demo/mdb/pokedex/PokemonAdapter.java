package com.demo.mdb.pokedex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.demo.mdb.pokedex.Pokedex;
import com.demo.mdb.pokedex.R;

import java.util.ArrayList;

import static com.demo.mdb.pokedex.R.id.pokemonImageView;


/**
 * Created by aneeshjindal on 2/26/16.
 */
public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.CustomViewHolder> {

    private Context context;
    public ArrayList<Pokedex.Pokemon> pokemonArrayList;

    public PokemonAdapter(Context context, ArrayList<Pokedex.Pokemon> pokemon) {
        this.context = context;
        pokemonArrayList = pokemon;
    }

    /* In simplified terms, a ViewHolder is an object that holds the pointers to the views in each
    each row. What does that mean? Every row has a TextView, ImageView, and CheckBox. Each row has
    a ViewHolder, and that ViewHolder holder these 3 views in it (hence "view holder").
    This function returns a single ViewHolder; it is called once for every row.
    */
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        This "inflates" the views, using the layout R.layout.row_view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view, parent, false);
        return new CustomViewHolder(view);
    }

    /* This function takes the previously made ViewHolder and uses it to actually display the
    data on the screen. Remember how the holder contains (pointers to) the 3 views? By doing, for
    example, "holder.imageView" we are accessing the imageView for that row and setting the
    ImageResource to be the corresponding image for that subject.
     */
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
//                    SchoolSubject schoolSubject = schoolSubjectsArray.get(getAdapterPosition());
//                    Toast.makeText(context, schoolSubject.name, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}