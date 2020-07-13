package com.example.projetandroidbinome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class VerifForgotPassword extends AppCompatActivity {

    TextView backToConnection,resend;
    EditText n1,n2,n3,n4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verif_forgot_password);

        backToConnection=findViewById(R.id.connexionback);
        resend=findViewById(R.id.resend);


        n1=findViewById(R.id.number1);
        n2=findViewById(R.id.number2);
        n3=findViewById(R.id.number3);
        n4=findViewById(R.id.number4);

        resend.setPaintFlags(resend.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);



        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        backToConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =new Intent(VerifForgotPassword.this,Connexion.class);
                startActivity(intent);
            }
        });


        n4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                Intent intent  =new Intent(VerifForgotPassword.this,ResetYourPassword.class);
                startActivity(intent);

            }
        });



    }
}
