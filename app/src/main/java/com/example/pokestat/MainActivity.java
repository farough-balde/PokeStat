package com.example.pokestat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity

{
    public static String  TAG= MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "com.example.android.PokeStat.extra.Message";
    private static final String TEXT_STATE = "currentText";
    private EditText PokemonName;
    private TextView mview;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static void verifyStoragePermissions(Activity activity)

    {
        // Vérifie si nous avons les droits d'écriture
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED)

        {
            // Aïe, il faut les demander à l'utilisateur
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

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
        mview = findViewById(R.id.textViewInput);
        Log.d(TAG,"onCreate");


        if(savedInstanceState!=null)
        {
            PokemonName.setText(savedInstanceState.getString(TEXT_STATE));
            //mview.setText(savedInstanceState.getString(TEXT_STATE));
        }

        verifyStoragePermissions(this);
    }


    public void m_exit(View view)

    {
        Log.d(TAG,"Finish");
        this.finish();
    }

    public void m_search(View view)
    {

        // Start The InfoPokemon Activity with an Extra
        Intent intent = new Intent(this,InfoPokemon.class);
        String message = PokemonName.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState)

    {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_STATE,PokemonName.getText().toString());
        //outState.putString(TEXT_STATE,PokemonName.getText().toString());
        Log.d(TAG,"onSaveInstance");
    }
}