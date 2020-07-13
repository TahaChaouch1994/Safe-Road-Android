package com.example.projetandroidbinome;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import java.util.regex.Pattern;

import static com.example.projetandroidbinome.SignUpMail.sharedPrefFile;


public class Connexion extends AppCompatActivity {


    EditText email, password;
    Button signin, forgotpassword, signup;
    Context context;
    String loginPref;
    static public boolean testcon = false;

    private SharedPreferences mPreferences;






    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);



        email = findViewById(R.id.emailconenxion);
        password = findViewById(R.id.password);
        signin = findViewById(R.id.signin);
        forgotpassword = findViewById(R.id.forgotpassword);
        signup = findViewById(R.id.signup);


        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
       String  loginPref = mPreferences.getString("email", "");



       if(loginPref.length()>0)
       {
           Intent intent  =new Intent(Connexion.this,MainActivity.class);
           startActivity(intent);

       }



        System.out.println(testcon);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String emailsize=email.getText().toString();
               String passwordsize=password.getText().toString();

               if (emailsize.length()== 0 || (!isValidMail(emailsize)))
               {
                   Toast.makeText(getApplicationContext(),"Invalid Email",Toast.LENGTH_LONG).show();
               }


               else if(passwordsize.length() == 0)
               {
                   Toast.makeText(getApplicationContext(),"Invalid Password",Toast.LENGTH_LONG).show();
               }

               else {
                   new Connexiontest().execute();
               }


            }
        });


        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =new Intent(Connexion.this,ForgotPassword.class);
                startActivity(intent);

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent  =new Intent(Connexion.this,SignUp.class);
                startActivity(intent);

            }
        });







    /*    signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

*/


        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String mail=(email.getText()).toString();

                if (!isValidMail(mail))
                {
                   email.setTextColor(Color.RED);
                }
                else
                {
                    email.setTextColor(Color.WHITE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {



            }
        });



      /*  String mail=(email.getText()).toString();
        if (!isValidMail(mail))
        {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "This is a message displayed in a Toast",
                    Toast.LENGTH_SHORT);
            toast.show();
        }*/




    }

    public static boolean isValidMail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }





    //CONNECTION


    private class Connexiontest extends AsyncTask<Void,Void,Void> {
        String data ="";

        String dataParsed = "";
        String singleParsed ="";
        EditText emailCon,passwordCon;
        String vEmail,vPassword;



        @Override
        protected void onPreExecute() {

            emailCon = findViewById(R.id.emailconenxion);
            passwordCon = findViewById(R.id.password);

            vEmail=emailCon.getText().toString();
            vPassword=passwordCon.getText().toString();

        }




        @Override
        protected Void doInBackground(Void... voids) {
            try {
                 String common="firsta";






                URL url = new URL("http://192.168.43.216:3003/user/"+vEmail+"/"+vPassword);
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
            System.out.println(data);

            if(data.toString().equals("true"))
            {


                mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                preferencesEditor.putString("email", vEmail);
                preferencesEditor.putString("mdp",vPassword);
                preferencesEditor.apply();
                Intent intent  =new Intent(Connexion.this,MainActivity.class);
                startActivity(intent);
            }

            else
            {System.out.println("Register GOOD");
            Toast.makeText(getApplicationContext(),"Wrong Password or Email",Toast.LENGTH_LONG).show();}



        }



    }





}
