package com.example.groupproject_groceryapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;



// Adapter - specify type of data, create each view visible within RecyclerView, bind each view to a piece of data
// this adapter needs to create each card and bind data to it
// tell the adapter what data to use by passing ids via its constructor

// RecyclerView - uses adapter to display data; includes a layout manager to specify how data should be positioned
// this RecyclerView needs to display a set of cards(ViewHolders), each card containing image and name

// ViewHolder - inner class of adapter; tells adapter which views to use for the data items

// onBindViewHolder - adds data; gets called when RecyclerView needs to display data in the ViewHolder

/** {@link RecyclerView.Adapter} that can display a {@link Recipe}
*/
public class CaptionedImagesAdapter
        extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder> {

    // adapter to take recipe names and images
    private ArrayList<Recipe> recipeDataSet;

    private ArrayList<String> ids;
    private ArrayList<String> names;
    private ArrayList<String> imageUris;
    private ArrayList<String> instructions;
    private ArrayList<ArrayList> ingredients;
    private Listener listener;
    private RecipesFragmentViewModel viewModel;


    interface Listener {
        void onClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public CaptionedImagesAdapter(ArrayList<Recipe> mDataSet) {

//        this.names = names;
//        this.imageUris = imageUris;

        ids = new ArrayList<>();
        ingredients = new ArrayList<>();
        instructions = new ArrayList<>();
        names = new ArrayList<>();
        imageUris = new ArrayList<>();



        viewModel = new RecipesFragmentViewModel();
        viewModel.getRecipes((ArrayList<Recipe> recipeDataSet) -> {
            for(int i = 0; i < recipeDataSet.size(); i++){
                ids.add(recipeDataSet.get(i).getId());
                names.add(recipeDataSet.get(i).getName());
                imageUris.add(recipeDataSet.get(i).getImageUri());
                instructions.add(recipeDataSet.get(i).getInstructions());
                ingredients.add((ArrayList) recipeDataSet.get(i).getIngredients());
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public CaptionedImagesAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType){
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_captioned_image, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        CardView cardView = holder.cardView;

        //image
        String imageUri = imageUris.get(position);
        ImageView imageView = (ImageView)cardView.findViewById(R.id.card_image);
        Picasso.get().load(imageUri).into(imageView);

        //name
        imageView.setContentDescription(names.get(position));
        TextView textView = (TextView)cardView.findViewById(R.id.card_title);
        textView.setText(names.get(position));

        //listen for clicks on card
        cardView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(position);
            }
        });
    }
}