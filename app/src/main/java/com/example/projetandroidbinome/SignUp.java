package com.example.projetandroidbinome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {

    EditText phonenumber,password,repeatpassword;
    Button   register;
    TextView switchtoemail,connexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        phonenumber=findViewById(R.id.phone_number);
        password=findViewById(R.id.password);
        repeatpassword=findViewById(R.id.repeatpassword);

        register=findViewById(R.id.register);
        switchtoemail=findViewById(R.id.switchtomail);
        connexion=findViewById(R.id.connexionback);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =new Intent(SignUp.this,VerificationCodePage.class);
                startActivity(intent);

            }
        });

        switchtoemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =new Intent(SignUp.this,SignUpMail.class);
                startActivity(intent);

            }
        });

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =new Intent(SignUp.this,Connexion.class);
                startActivity(intent);
            }
        });

    }
}
