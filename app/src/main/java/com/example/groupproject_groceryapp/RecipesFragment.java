package com.example.groupproject_groceryapp;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
 * displaying data to user.
 */
public class RecipesFragment extends Fragment {

    private static final String TAG = RecipesFragment.class.getSimpleName();


    private ArrayList<Recipe> mDataSet;
    public static final String ARG_DATA_SET = "data-set";
    private ArrayList<String> names;
    private ArrayList<String> imageUris;

    // Mandatory empty constructor to instantiate frag (eg: screen orientation change)
    public RecipesFragment() {
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//        Log.i(TAG, "onCreate()");
//    }

    // RecyclerView uses adapter and layout manager to display recipes in grid
    // Calls onCreateView each time Android needs the fragment's layout
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recipeRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_recipes,
                container, false);
        if (getArguments() != null) {
            mDataSet = getArguments().getParcelableArrayList(ARG_DATA_SET);
        }






        names = new ArrayList<>();
        imageUris = new ArrayList<>();

        for(int i = 0; i < names.size(); i++) {
            names.add(mDataSet.get(i).getName());
            imageUris.add(mDataSet.get(i).getImageUri());
        }


        // set adapter
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(mDataSet);
        recipeRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recipeRecycler.setLayoutManager(layoutManager);

        // set listener
        adapter.setListener(position -> {
            Intent intent = new Intent(getActivity(), RecipeDetailActivity.class);
            intent.putExtra(RecipeDetailActivity.EXTRA_RECIPE_ID, position);
            getActivity().startActivity(intent);
        });

        return recipeRecycler;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
