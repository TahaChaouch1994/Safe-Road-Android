package com.example.projetandroidbinome;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import static com.example.projetandroidbinome.SignUpMail.sharedPrefFile;


public class NextSignUp extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {



    private SharedPreferences mPreferences;
    private TextView dateText;
    private EditText fname,lname,emailNumber;
    Button finish,calendar;
    RadioGroup malefemale;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_sign_up);



        dateText = findViewById(R.id.date_text);

        emailNumber = findViewById(R.id.emailnumber);

        fname=findViewById(R.id.firstname);
        lname=findViewById(R.id.lastname);

        finish=findViewById(R.id.finish);
        calendar=findViewById(R.id.show_dialog);

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        malefemale =findViewById(R.id.radioGrp);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  Intent intent  =new Intent(NextSignUp.this,MainActivity.class);
                startActivity(intent);*/

              new afterRegisterTest().execute();

            }
        });
    }




    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }



    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = month + "_" + dayOfMonth + "_" + year;
        dateText.setText(date);
    }



    private class afterRegisterTest extends AsyncTask<Void,Void,Void> {
        String data = "";

        String dataParsed = "";
        String singleParsed = "";
        EditText emailCon, passwordCon;
        String vEmail, vPassword;


        String loginPref, vfname, vlname, vemailNumber, radioString, vdatetext;


        @Override
        protected void onPreExecute() {


            fname = findViewById(R.id.firstname);

            lname = findViewById(R.id.lastname);
            emailNumber = findViewById(R.id.emailnumber);

            int selectedId = malefemale.getCheckedRadioButtonId();
            RadioButton radioButton = findViewById(selectedId);
            radioString = radioButton.getText().toString();

            dateText = findViewById(R.id.date_text);


            vfname = fname.getText().toString();
            vlname = lname.getText().toString();
            vemailNumber = emailNumber.getText().toString();
            vdatetext = dateText.getText().toString();


        }


        @Override
        protected Void doInBackground(Void... voids) {
            try {

                mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
                loginPref = mPreferences.getString("email", "");
                URL url = new URL("http://192.168.43.216:3003/updateuser/" + loginPref + "/" + vfname + "/" + vlname + "/" + vemailNumber + "/" + radioString + "/" + vdatetext);
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


            Intent intent = new Intent(NextSignUp.this, MainActivity.class);
            startActivity(intent);

        }

    }



    }
