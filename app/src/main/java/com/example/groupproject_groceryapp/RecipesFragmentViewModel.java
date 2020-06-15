package com.example.groupproject_groceryapp;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.function.Consumer;

public class RecipesFragmentViewModel {

    private FirebaseRecipeModel recipeModel;

    public RecipesFragmentViewModel() {
        recipeModel = new FirebaseRecipeModel();
    }

    public void getRecipes(Consumer<ArrayList<Recipe>> responseCallback) {
        recipeModel.getRecipes(
                (DataSnapshot dataSnapshot) -> {
                    ArrayList<Recipe> recipes = new ArrayList<>();
                    for (DataSnapshot recipeSnapshot : dataSnapshot.getChildren()) {
                        Recipe item = recipeSnapshot.getValue(Recipe.class);
                        assert item != null;
                        item.id = recipeSnapshot.getKey();
                        recipes.add(item);
                    }
                    responseCallback.accept(recipes);
                },
                (databaseError -> System.out.println("Error reading Recipes: " + databaseError))
        );
    }

    public void clear() {
        recipeModel.clear();
    }
}
