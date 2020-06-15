package com.example.groupproject_groceryapp;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A top level fragment representing a list of Recipes. Upon selection, it takes care of
 * displaying data to user. Activities containing this fragment MUST implement
 * {@link OnRecipesFragmentInteractionListener} interface.
 */
public class RecipesFragment extends Fragment {

    private static final String TAG = RecipesFragment.class.getSimpleName();

    private ArrayList<Recipe> mDataSet;
    public static final String ARG_DATA_SET = "data-set";
    private ArrayList<String> ids;
    private ArrayList<String> names;
    private ArrayList<String> imageUris;
    private RecipesFragmentViewModel viewModel;

    // Mandatory empty constructor to instantiate frag (eg: screen orientation change)
    public RecipesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mDataSet = getArguments().getParcelableArrayList(ARG_DATA_SET);
        }
    }

    // RecyclerView uses adapter and layout manager to display recipes in grid
    // Calls onCreateView each time Android needs the fragment's layout
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recipeRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_recipes,
                container, false);

        // add names
        names = new ArrayList<>();
        imageUris = new ArrayList<>();

        viewModel = new RecipesFragmentViewModel();
        viewModel.getRecipes((ArrayList<Recipe> recipeDataSet) -> {
            for(int i = 0; i < recipeDataSet.size(); i++){
                ids.add(recipeDataSet.get(i).getId());
                names.add(recipeDataSet.get(i).getName());
                imageUris.add(recipeDataSet.get(i).getImageUri());

        for(int i = 0; i < recipeNames.length; i++) {
            recipeNames[i] = Recipe.recipes[i].getName();
        }

        // add images
        int[] recipeImages = new int[Recipe.recipes.length];

        for (int i = 0; i < recipeImages.length; i++) {
            recipeImages[i] = Recipe.recipes[i].getImageUri();
        }

        // set adapter
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(mDataSet);
        recipeRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recipeRecycler.setLayoutManager(layoutManager);

        // set listener
        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), RecipeDetailActivity.class);
                intent.putExtra(RecipeDetailActivity.EXTRA_RECIPE_ID, position);
                getActivity().startActivity(intent);
            }
        });

        return recipeRecycler;
    }
}
