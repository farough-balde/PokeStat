package com.example.pokestat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity

{
    public static String  TAG="PokeStat";
    private EditText PokemonName;
    private Button  search;
    private Button sorti;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PokemonName = findViewById(R.id.editTextTextPersonName);
        search= findViewById(R.id.buttonSearch);
        sorti= findViewById(R.id.buttonExit);
    }
}