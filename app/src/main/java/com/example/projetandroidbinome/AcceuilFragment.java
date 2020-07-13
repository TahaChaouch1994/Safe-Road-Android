package com.example.projetandroidbinome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.projetandroidbinome.entities.post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class AcceuilFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SharedPreferences mPreferences;
    private String loginPref;
    private ArrayList<post> exempleList = new ArrayList<>();
    private String firstname,lastname,adresse,numero,facebook,iduserroad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        View v= inflater.inflate(R.layout.activity_acceuil_fragment, container, false);





        mRecyclerView = v.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new PostListAdapter(exempleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mPreferences = AcceuilFragment.this.getActivity().getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        loginPref = mPreferences.getString("email", "");



        new chargeProfile().execute();
        new chargeAcceuil().execute();




        return v;
    }



    //Charge Profile


    private class chargeProfile extends AsyncTask<Void,Void,Void> {



        String data ="";
        String dataParsed = "";
        String singleParsed ="";
        Context context;




        @Override
        protected void onPreExecute() {


        }




        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("http://192.168.43.216:3003/usergetemail/"+loginPref);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while(line != null){
                    line = bufferedReader.readLine();
                    data = data + line;
                }

                JSONArray JA = null;
                try {
                    JA = new JSONArray(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for(int i =0 ;i <JA.length(); i++){
                    JSONObject JO = (JSONObject) JA.get(i);
                    firstname = JO.get("firstname").toString();
                    lastname = JO.get("lastname").toString();
                    adresse = JO.get("adresse").toString();
                    numero = JO.get("numero").toString();
                    facebook = JO.get("facebook").toString();
                    iduserroad= JO.get("id").toString();

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }




        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);



            mPreferences = AcceuilFragment.this.getActivity().getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.putString("firstname", firstname);
            preferencesEditor.putString("lastname",lastname);
            preferencesEditor.putString("adresse",adresse);
            preferencesEditor.putString("numero",numero);
            preferencesEditor.putString("facebook",facebook);
            preferencesEditor.putString("iduserroad",iduserroad);

            preferencesEditor.apply();
           // System.out.println(firstname);






        }



    }








    //Charge Profile


    private class chargeAcceuil extends AsyncTask<Void,Void,Void> {



           String data="",iduserdeclare,titre,kcontenu,ffname,llname;



        @Override
        protected void onPreExecute() {


        }




        @Override
        protected Void doInBackground(Void... voids) {


            try {
                URL url = new URL("http://192.168.43.216:3003/usergetdeclare2");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while(line != null){
                    line = bufferedReader.readLine();
                    data = data + line;
                }

                JSONArray JA = null;
                try {
                    JA = new JSONArray(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for(int i =0 ;i <JA.length(); i++){
                    JSONObject JO = (JSONObject) JA.get(i);
                    iduserdeclare = JO.get("iduserdeclare").toString();
                    titre = JO.get("titre").toString();
                    kcontenu = JO.get("contenu").toString();
                    ffname = JO.get("firstname").toString();
                    llname  = JO.get("lastname").toString();

                    String ffllname=ffname+" "+llname;

                   exempleList.add(new post(R.drawable.goooglemap,titre,kcontenu,R.drawable.firas,ffllname));


                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }




        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

           /*  int ll = exempleList.size();
             String ff = String.valueOf(ll);
             Toast.makeText(getActivity(),ff,Toast.LENGTH_LONG).show(); */



        }



    }



}

