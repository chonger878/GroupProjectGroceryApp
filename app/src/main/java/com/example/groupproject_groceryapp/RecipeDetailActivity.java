package com.example.groupproject_groceryapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


// get selected recipe id from intent (from recipes fragment onclick) that starts this activity

// Display details of selected recipe
public class RecipeDetailActivity extends AppCompatActivity {
    public static final String EXTRA_RECIPE_ID = "recipeId";
    private ArrayList<Recipe> recipeDataSet;

    public RecipeDetailActivity(ArrayList<Recipe> recipeDataSet) {
        this.recipeDataSet = recipeDataSet;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        //Set the toolbar as the activity's app bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Display details of the chosen recipe
        int recipeId = (Integer)getIntent().getExtras().get(EXTRA_RECIPE_ID);

        //name
        String recipeName = recipeDataSet.get(recipeId).getName();
        TextView tvName = findViewById(R.id.recipe_title);
        tvName.setText(recipeName);


        //ingredients loop toString
        StringBuilder recipeIngredients = new StringBuilder();
        for(int i = 0; i < recipeDataSet.size(); i++) {
            recipeIngredients.append(recipeDataSet.get(i).getIngredients());
            recipeIngredients.append("/n");
        }
        TextView tvIngredients = findViewById(R.id.recipe_ingredients);
        tvIngredients.setText(recipeIngredients);

        //instructions
        String recipeInstructions = recipeDataSet.get(recipeId).getInstructions();
        TextView tvInstructions = findViewById(R.id.recipe_instructions);
        tvInstructions.setText(recipeInstructions);

        //image
        String recipeImage = recipeDataSet.get(recipeId).getImageUri();
        ImageView imageView = findViewById(R.id.recipe_image);
        Picasso.get().load(recipeImage).into(imageView);

        imageView.setContentDescription(recipeName);
    }
}
