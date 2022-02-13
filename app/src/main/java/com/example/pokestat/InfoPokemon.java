package com.example.pokestat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class InfoPokemon extends AppCompatActivity

{

    public static String TAG = InfoPokemon.class.getSimpleName();
    private TextView mName;
    private TextView mType;
    private TextView mPoids;
    private TextView mTaille;

    private final String PokemonName_KEY = "pokemon";
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.Historique.pokestat";
    private Set<String> searchedPokemonName;


    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pokemon);


        mType= findViewById(R.id.textViewTypedisp);
        mPoids=findViewById(R.id.textViewPoidsDisp);
        mTaille= findViewById(R.id.textViewTailleDisp);
        mName= findViewById(R.id.textViewNameDisp);
        Log.d(TAG,"OnCreate");




        //getting Pokemon Name from the intent created by MainActivity
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        mName.setText(message);
        Log.d(TAG,"Name of the Pokemon " + message);




        // Call for PokeRequest from The main Thread
        new PokeRequest(mName.getText().toString(),InfoPokemon.this).execute();



    }

    public void ViewOnWebsite(View view)

    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String url = "https://www.pokepedia.fr/"+ mName.getText().toString();
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    public void ExtraireDonnee(View view)
    {

        write_historic_in_file();
    }

    public void Fermer(View view)
    {

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();
        finish();
    }



    public void toast(String msg)
    {
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }


    public void vibrate(long duration_ms)

    {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if(duration_ms < 1)
            duration_ms = 1;
        if(v != null && v.hasVibrator())
        {
            // Attention changement comportement avec API >= 26 (cf doc)
            if(Build.VERSION.SDK_INT >= 26)
            {
                v.vibrate(VibrationEffect.createOneShot(duration_ms, VibrationEffect.DEFAULT_AMPLITUDE));
            }
            else
            {
                v.vibrate(duration_ms);
            }
        }
        // sinon il n'y a pas de mécanisme de vibration
    }

    public void reload_historic()
    {

       // SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
       // searchedPokemonName = mPreferences.getStringSet(sharedPrefFile, new TreeSet<>());

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        searchedPokemonName= mPreferences.getStringSet(PokemonName_KEY, new TreeSet<>());


    }

    public void display_historic()
    {
        Log.d(TAG,"Historique ("+ (new Date())+ ") size= "+searchedPokemonName.size()+":  ");
        for (String item : searchedPokemonName)

        {
            Log.d(TAG,"\t- " + item);
        }
    }

    @Override
    protected void onPause()

    {
        super.onPause();
        Log.d(TAG,"On Pause");


        /*SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        searchedPokemonName.add(mName.getText().toString());
        sharedPref.edit().putStringSet(PokemonName_KEY,searchedPokemonName);
        sharedPref.edit().commit();*/

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        searchedPokemonName.add(mName.getText().toString());
        preferencesEditor.putStringSet(PokemonName_KEY, searchedPokemonName);
        preferencesEditor.apply();


    }

    public void write_historic_in_file()

    {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File fileout = new File(folder, "pokestat_historic.txt");
        try (FileOutputStream fos = new FileOutputStream(fileout)) {
            PrintStream ps = new PrintStream(fos);
            ps.println("Start of my historic");
            ps.println(Log.d(TAG,"Historique ("+ (new Date())+ ") size= "+searchedPokemonName.size()+":  "));
            for (String item : searchedPokemonName)

            {
                ps.println(Log.d(TAG,"\t- " + item));
            }

            // TODO: YOU MUST COMPLETE ICI

            ps.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG,"File not found",e);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG,"Error I/O",e);
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
    protected void onRestart()
    {
        super.onRestart();
        Log.d(TAG,"onRestart");
    }

    public TextView getmName() {
        return mName;
    }

    public TextView getmType() {
        return mType;
    }

    public TextView getmPoids() {
        return mPoids;
    }

    public TextView getmTaille() {
        return mTaille;
    }

}