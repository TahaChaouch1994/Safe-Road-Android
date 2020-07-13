package com.example.projetandroidbinome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ResetYourPassword extends AppCompatActivity {

    EditText password,repeatpassword;
    Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_your_password);

        password=findViewById(R.id.passwd);
        repeatpassword=findViewById(R.id.repeatpasswd);

        finish=findViewById(R.id.finish);



        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =new Intent(ResetYourPassword.this,Connexion.class);
                startActivity(intent);
            }
        });


    }
}
