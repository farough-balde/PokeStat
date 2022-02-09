package com.example.pokestat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class InfoPokemon extends AppCompatActivity

{

    public static String TAG = InfoPokemon.class.getSimpleName();
    private TextView mName;
    private TextView mType;
    private TextView mPoids;
    private TextView mTaille;


    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pokemon);

        mName= findViewById(R.id.textViewNameDisp);
        mType= findViewById(R.id.textViewTypedisp);
        mPoids=findViewById(R.id.textViewPoidsDisp);
        mTaille= findViewById(R.id.textViewTailleDisp);


        Intent intent = new Intent();
        String namePokemon = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        mName.setText(namePokemon);
        Log.d(TAG,namePokemon + "fuckupiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
    }
}