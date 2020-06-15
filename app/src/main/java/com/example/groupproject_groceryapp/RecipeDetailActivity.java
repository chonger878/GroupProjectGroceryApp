package com.example.groupproject_groceryapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;


// get selected recipe id from intent (from recipes fragment onclick) that starts this activity

// Display details of selected recipe
public class RecipeDetailActivity extends AppCompatActivity {
    public static final String EXTRA_RECIPE_ID = "recipeId";
    private ArrayList<Recipe> recipeDataSet;

    private ArrayList<String> ids;
    private ArrayList<String> names;
    private ArrayList<String> imageUris;
    private ArrayList<String> instructions;
    private ArrayList<ArrayList> ingredients;

    private RecipesFragmentViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        //Set the toolbar as the activity's app bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Display details of the chosen recipe
        int recipeId = (Integer)getIntent().getExtras().get(EXTRA_RECIPE_ID);




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





        //name
        String recipeName = Recipe.recipes[recipeId].getName();
        TextView tvName = (TextView)findViewById(R.id.recipe_title);
        tvName.setText(recipeName);

        //ingredients
        String recipeIngredients = Recipe.get(recipeId).getIngredients();
        TextView tvIngredients = (TextView)findViewById(R.id.recipe_ingredients);
        tvIngredients.setText(recipeIngredients);

        //instructions
        String recipeInstructions = Recipe.recipes[recipeId].getInstructions();
        TextView tvInstructions = (TextView)findViewById(R.id.recipe_instructions);
        tvInstructions.setText(recipeInstructions);

        //image
        int recipeImage = Recipe.recipes[recipeId].getImageUri();
        ImageView imageView = (ImageView)findViewById(R.id.recipe_image);
        imageView.setImageDrawable(ContextCompat.getDrawable(this, recipeImage));

        imageView.setContentDescription(recipeName);
    }
}
