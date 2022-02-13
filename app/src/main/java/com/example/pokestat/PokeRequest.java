package com.example.pokestat;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import com.example.pokestat.InfoPokemon;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class PokeRequest extends AsyncTask<Void,Integer,Void>
{
    private String name;
    private String restype;
    private String resname;
    private String resweight;
    private String ressize;
    private ProgressDialog progressDialog;
    private InfoPokemon context;

    public PokeRequest(String name, InfoPokemon contextParam)
    {
        this.name = name;
        restype = "<none>";
        resname = "<none>";
        resweight = "<none>";
        ressize = "<none>";
        context = contextParam;

    }


    @Override
    protected void onPreExecute()

    {
        super.onPreExecute();
        progressDialog = new ProgressDialog(this.context);
        progressDialog.show();
    }


    @Override
    protected void onProgressUpdate(Integer... values)

    {
        // Inutile ici, cf doc
        super.onProgressUpdate(values);
    }


    @Override
    protected Void doInBackground(Void... voids)

    {
        // Se fait en background du thread UI
        try
        {



            Document doc = Jsoup.connect("https://www.pokepedia.fr/" + name).get();
            Element tableinfo = doc.selectFirst("table.tableaustandard");

            Elements names = tableinfo.select("th.entêtesection");
            for (Element e : names)
            {
                resname = e.ownText();
                Log.v(InfoPokemon.TAG,"Entete section: " + resname);
            }

            Log.v(InfoPokemon.TAG,"=====>>>>>  FINAL Entete section: " + resname);

            Elements rows = tableinfo.select("tr");
            for (Element row : rows)
            {
                Log.v(InfoPokemon.TAG,"=====>>>>>  new line. ");
                if(row.select("a[title*=taille]").size() > 0)
                {
                    Element target = row.selectFirst("td");
                    if(target != null)
                    {
                        ressize= target.ownText();
                        Log.v(InfoPokemon.TAG,"=====>>>>>  Find a size: " + ressize);
                    }
                    else
                        Toast.makeText(new InfoPokemon(),R.string.error_no_dom_entity, Toast.LENGTH_LONG).show();
                }

                if(row.select("a[title*=poids]").size() > 0)
                {
                    Element target = row.selectFirst("td");
                    if(target != null)
                    {
                        resweight = target.ownText();
                        Log.v(InfoPokemon.TAG,"=====>>>>>  Find a weight: " + resweight);
                    }
                    else
                        Toast.makeText(context,R.string.error_no_dom_entity, Toast.LENGTH_LONG).show();
                }

            }


            Elements elems = tableinfo.select("a[title*=type]");
            ArrayList<String> types = new ArrayList<>();
            for (Element e: elems)
            {
                if(!e.attr("title").equalsIgnoreCase("Type"))

                {
                    String rawtype = e.attr("title");
                    String type = rawtype.replace(" (type)","");
                    types.add(type);
                    Log.v(InfoPokemon.TAG,"\tFind type: " +type);
                }
            }
            restype = types.stream().collect(Collectors.joining(" - "));
        } catch (IOException e)

        {
            Log.e(InfoPokemon.TAG,"Error during connection...",e);
            // e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) { // S'exécute sur le ThreadUI après doInBackground
        super.onPostExecute(aVoid);
        // ATTENTION, il faut adapter le code ci-dessous avec vos controles graphiques.
        context.getmName().setText(resname);
        context.getmType().setText(restype);
        context.getmTaille().setText(ressize);
        context.getmPoids().setText(resweight);
        Toast.makeText(context, R.string.end_request, Toast.LENGTH_SHORT).show();
        //vibrate(1000);
        progressDialog.dismiss();
       //if(context.onSaveInstanceState())
        context.reload_historic();
        context.display_historic();
        context.write_historic_in_file();
        // c'est ici que vous devrez ajouter l'écriture de votre fichier en FIN de sujet!!!




    }
}
