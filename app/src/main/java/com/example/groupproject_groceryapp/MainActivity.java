package com.example.groupproject_groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    }

    public void goToSecondActivity(View view){
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);

    }

    //public void toAllergies(View view){
    //    Intent intent = new Intent(MainActivity.this, AllergiesActivity.class);
    //    startActivity(intent);
    //}


}
