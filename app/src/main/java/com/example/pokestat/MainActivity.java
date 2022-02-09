package com.example.pokestat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity

{
    public static String  TAG= MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "com.example.android.PokeStat.extra.Message";
    private EditText PokemonName;

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG,"onDestroy System");
    }

    @Override
    protected void onStart()

    {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.d(TAG,"onRestart");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PokemonName = findViewById(R.id.editTextTextPersonName);


        Log.d(TAG,"onCreate");
    }

    public void m_exit(View view)

    {
        Log.d(TAG,"onDestroy");
        this.finish();
    }

    public void m_search(View view)
    {

        Intent intent = new Intent(this,InfoPokemon.class);
        String message = PokemonName.getText().toString();
        Log.d(TAG,message+"maineeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        intent.putExtra(EXTRA_MESSAGE,message);
       // intent.putExtra("imputpokemonname", PokemonName.getText().toString());
        startActivity(intent);
    }
}