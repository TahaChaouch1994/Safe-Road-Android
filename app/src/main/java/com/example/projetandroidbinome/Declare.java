package com.example.projetandroidbinome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetandroidbinome.entities.post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.example.projetandroidbinome.SignUpMail.sharedPrefFile;

public class Declare extends Fragment {

    private EditText titre,description,imeistolen;
    private Button map,plus,validate;
    private SharedPreferences mPreferences;
    private String IMEI="",iduserroad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.activity_declare, container, false);


        titre=v.findViewById(R.id.titledeclare);
        description=v.findViewById(R.id.descriptiondeclare);
        imeistolen=v.findViewById(R.id.imei);

        map=v.findViewById(R.id.googlemapbutton);
        plus=v.findViewById(R.id.plus);
        validate=v.findViewById(R.id.validatedeclare);

        mPreferences = getActivity().getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        iduserroad = mPreferences.getString("iduserroad", "");



        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String vimeistolen=imeistolen.getText().toString();

                if (vimeistolen.length() == 0)
                {
                    Toast.makeText(getActivity(),"IMEI cant be Empty",Toast.LENGTH_LONG).show();

                }


                else if (IMEI.length()==0) {
                    IMEI=vimeistolen;
                }

                else

                {
                    IMEI = IMEI + "/" + vimeistolen;

                }
                Toast.makeText(getActivity(),"IMEI ajouté avec succès",Toast.LENGTH_LONG).show();
                imeistolen.setText("");

            }
        });

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(titre.getText().toString().length()==0)
                {
                    Toast.makeText(getActivity(),"Title cannot be empty",Toast.LENGTH_LONG).show();
                }
                else if(description.getText().toString().length()==0)
                {
                    Toast.makeText(getActivity(),"Description cannot be empty",Toast.LENGTH_LONG).show();
                }
                else {
                    new declareIncident().execute();
                    titre.setText("");
                    description.setText("");
                    Toast.makeText(getActivity(),"Post Succeed",Toast.LENGTH_LONG).show();

                }

            }
        });





        return v;
    }






    //DECLARE INCIDENT


    private class declareIncident extends AsyncTask<Void,Void,Void> {

        String data = "";
        String dataParsed = "";
        String singleParsed = "";
        String vtitre,vcontenu,vstolen,latitude="0",longitude="0";


        @Override
        protected void onPreExecute() {
            vtitre=titre.getText().toString();
            vcontenu=description.getText().toString();




        }


        @Override
        protected Void doInBackground(Void... voids) {
           try {


                URL url = new URL("http://192.168.43.216:3003/adddeclare/"+iduserroad+"/"+vtitre+"/"+vcontenu+"/"+latitude+"/"+longitude+"/"+IMEI );
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";

                while (line != null) {
                    line = bufferedReader.readLine();
                    data = data + line;
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

           //Toast.makeText(getActivity(),iduserroad,Toast.LENGTH_LONG).show();


        }
    }


}












