package com.example.projetandroidbinome;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

public class SignUpMail extends AppCompatActivity {

    private SharedPreferences mPreferences;
    public static final String sharedPrefFile = "tn.esprit.myapplication";
    EditText phonenumber,password,repeatpassword;
    Button register;
    TextView switchtophone,connexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_mail);



        phonenumber=findViewById(R.id.email_register);
        password=findViewById(R.id.password_registermail);
        repeatpassword=findViewById(R.id.repeatpasswordemail);

        register=findViewById(R.id.register);
        switchtophone=findViewById(R.id.switchtophone);
        connexion=findViewById(R.id.connexionback);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               String firstEmail = phonenumber.getText().toString();
               String firstPassword = password.getText().toString();
               String secondPassword = repeatpassword.getText().toString();

               if( firstEmail.length() == 0 )
               {
                   Toast.makeText(getApplicationContext(),"Email can't be empty",Toast.LENGTH_LONG).show();
               }
               else if(firstPassword.length() == 0  )
               {
                   Toast.makeText(getApplicationContext(),"Password can't be empty",Toast.LENGTH_LONG).show();
               }

               else if(!firstPassword.equals(secondPassword))
               {
                   Toast.makeText(getApplicationContext(),"Passwords don't match",Toast.LENGTH_LONG).show();
               }

               else {
                   new RegisterTest().execute();
               }

            }
        });

        switchtophone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent  =new Intent(SignUpMail.this,SignUp.class);
                startActivity(intent);
            }
        });

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =new Intent(SignUpMail.this,Connexion.class);
                startActivity(intent);
            }
        });




         phonenumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String mail=(phonenumber.getText()).toString();

                if (!isValidMail(mail))
                {
                    phonenumber.setTextColor(Color.RED);
                }
                else
                {
                    phonenumber.setTextColor(Color.WHITE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {



            }
        });

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




    //SIGNUP


    private class RegisterTest extends AsyncTask<Void,Void,Void> {
        String data ="";

        String dataParsed = "";
        String singleParsed ="";
        EditText emailCon,passwordCon;
        String vEmail,vPassword;



        @Override
        protected void onPreExecute() {

            emailCon = findViewById(R.id.email_register);
            passwordCon = findViewById(R.id.password_registermail);

            vEmail=emailCon.getText().toString();
            vPassword=passwordCon.getText().toString();

        }




        @Override
        protected Void doInBackground(Void... voids) {
            try {




                URL url = new URL("http://192.168.43.216:3003/newuserandroid/"+vEmail+"/"+vPassword);
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
                preferencesEditor.apply();


                Intent intent  =new Intent(SignUpMail.this,VerificationCodePage.class);
                startActivity(intent);
            }

            else
            {
                System.out.println("Register GOOD");
                Toast.makeText(getApplicationContext(),"Email already exists",Toast.LENGTH_LONG).show();
            }

        }



    }







}
