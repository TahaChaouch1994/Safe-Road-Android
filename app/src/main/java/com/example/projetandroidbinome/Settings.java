package com.example.projetandroidbinome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.Context.MODE_PRIVATE;
import static com.example.projetandroidbinome.SignUpMail.sharedPrefFile;

public class Settings extends Fragment {

    private SharedPreferences mPreferences;
    private Button prvt;
    private EditText vPassword1,vPassword,vPassword2;
    private String vPassword1v,vPasswordv,vPassword2v;
    String loginPref,password;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_settings, container, false);




        prvt = v.findViewById(R.id.savemdp);





        prvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPreferences = Settings.this.getActivity().getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
                loginPref = mPreferences.getString("email", "");
                password = mPreferences.getString("mdp", "");


                vPassword = Settings.this.getActivity().findViewById(R.id.ancienmdp);
                vPasswordv=vPassword.getText().toString();
                vPassword1 = Settings.this.getActivity().findViewById(R.id.newmdpp);
                vPassword1v=vPassword1.getText().toString();
                vPassword2= Settings.this.getActivity().findViewById(R.id.repeatmdp);
                vPassword2v=vPassword2.getText().toString();


                if((vPasswordv.length()==0) || vPassword1v.length()==0 || vPassword2v.length()==0)
                {
                    Toast.makeText(getActivity(),"Password cant be Empty",Toast.LENGTH_LONG).show();
                }
                else if(!(vPasswordv.equals(password)))
                {
                    Toast.makeText(getActivity(),"Your password is Wrong",Toast.LENGTH_LONG).show();
                }
                else if(!(vPassword1v.equals(vPassword2v)))
                {
                    Toast.makeText(getActivity(),"New Password Error",Toast.LENGTH_LONG).show();
                }

                else {
                    new changePassword().execute();
                    vPassword.setText("");
                    vPassword1.setText("");
                    vPassword2.setText("");
                }
            }
        });










        return v;

    }




    //Change Password


    private class changePassword extends AsyncTask<Void,Void,Void> {



        String data ="";



        @Override
        protected void onPreExecute() {

           mPreferences = Settings.this.getActivity().getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
            loginPref = mPreferences.getString("email", "");
            password = mPreferences.getString("mdp", "");

            vPassword = Settings.this.getActivity().findViewById(R.id.ancienmdp);
            vPasswordv=vPassword.getText().toString();
            vPassword1 = Settings.this.getActivity().findViewById(R.id.newmdpp);
            vPassword1v=vPassword1.getText().toString();
            vPassword2= Settings.this.getActivity().findViewById(R.id.repeatmdp);
            vPassword2v=vPassword2.getText().toString();

        }




        @Override
        protected Void doInBackground(Void... voids) {
           try {







                URL url = new URL("http://192.168.43.216:3003/changepassword/"+loginPref+"/"+vPassword1v);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";

                while(line != null){
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

           data = data.substring(1,5);








                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                preferencesEditor.putString("mdp",vPassword1v);
                preferencesEditor.apply();
                Toast.makeText(getActivity(),"Password Changed",Toast.LENGTH_LONG).show();






           // Toast.makeText(getActivity(),vPasswordv,Toast.LENGTH_LONG).show();




        }



    }
}
