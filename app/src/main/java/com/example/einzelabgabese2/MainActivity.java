package com.example.einzelabgabese2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setContentView(R.layout.content_main);

        final Button testen = findViewById(R.id.Testen);
        testen.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText matrikelNr = (EditText)findViewById(R.id.editText4);
                TextView ergebnis = (TextView)findViewById((R.id.Ergebnis));

                String a = matrikelNr.getText().toString();
                int erg = 0;

                for (int i = 0; i < a.length(); i++){
                    erg = erg + Integer.parseInt(String.valueOf(a.charAt(i)));
                }

                if(erg%2==0){
                    ergebnis.setText("Die Quersumme deiner Matrikelnummer ist gerade.");
                }
                else{
                    ergebnis.setText("Die Quersumme deiner Matrikelnummer ist ungerade.");
                }

            }
        });

        final Button senden = findViewById(R.id.Senden);
        senden.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText eingabeFeld = (EditText)findViewById(R.id.editText4);
                TextView erg = (TextView) findViewById((R.id.Ergebnis));
                String matrikelNr = eingabeFeld.getText().toString();

                TCPClient tcp = new TCPClient(matrikelNr);
                tcp.start();

                try{
                    tcp.join();
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }

                erg.setText(tcp.modifiedSentence);

            }
        });
    }







}
