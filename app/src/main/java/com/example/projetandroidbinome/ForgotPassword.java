package com.example.projetandroidbinome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ForgotPassword extends AppCompatActivity {

    EditText email;
    TextView backtoconnection;
    Button send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        email=findViewById(R.id.emailverif);

        backtoconnection=findViewById(R.id.connexionback);

        send=findViewById(R.id.send);





        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =new Intent(ForgotPassword.this,VerifForgotPassword.class);
                startActivity(intent);
            }
        });



        backtoconnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =new Intent(ForgotPassword.this,Connexion.class);
                startActivity(intent);
            }
        });



    }
}
