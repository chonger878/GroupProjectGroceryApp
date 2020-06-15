package com.example.groupproject_groceryapp;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// RecyclerView data source
@IgnoreExtraProperties
public class Recipe implements Parcelable {
    public String id;
    public String name;
    public String instructions;
    public ArrayList<String> ingredients;
    String imageUri = "https://en.wikipedia.org/wiki/Food#/media/File:Good_Food_Display_-_NCI_Visuals_Online.jpg";

    public Recipe() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Recipe(Parcel in) {
        name = in.readString();
        in.readStringList(ingredients);
        instructions = in.readString();
        id = in.readString();
        imageUri = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    // Getters
    public String getId() {
        return id;
    }
    public String getImageUri() {
        return imageUri;
    }
    public String getName() {
        return name;
    }
    public String getInstructions() {
        return instructions;
    }
    public List<String> getIngredients() {
        return ingredients;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    // tells parcel class how to write object
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }





    private ArrayList<Recipe> recipeDataSet;

    private ArrayList<String> ids;
    private ArrayList<String> names;
    private ArrayList<String> imageUris;
    private ArrayList<String> instructions;
    private ArrayList<ArrayList> ingredients;
    private RecipesFragmentViewModel viewModel;

    ids = new ArrayList<>();
    names = new ArrayList<>();
    imageUris = new ArrayList<>();
    instructions = new ArrayList<>();
    ingredients = new ArrayList<>();

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
