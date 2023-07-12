package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;

public class resultado extends AppCompatActivity{

    TextView res1,res2,res3,res4,res5,total;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);

        res1 = findViewById(R.id.res1);
        res2 = findViewById(R.id.res2);
        res3 = findViewById(R.id.res3);
        res4 = findViewById(R.id.res4);
        res5 = findViewById(R.id.res5);
        total=findViewById(R.id.total);

        // Obtener el Intent que inició esta actividad
        Intent intent = getIntent();

        // Recuperar los extras del Intent
        int rp1 = intent.getIntExtra("rp1", 0);
        int rp2 = intent.getIntExtra("rp2", 0);
        int rp3 = intent.getIntExtra("rp3", 0);
        int rp4 = intent.getIntExtra("rp4", 0);
        int rp5 = intent.getIntExtra("rp5", 0);

        if(rp1==1){
            res1.setText("¡ Corecto ! ");
        }
        else {
            res1.setText("¡ Incorrecto ! ");
        }
        if(rp1==2){
            res2.setText("¡ Corecto ! ");
        }
        else {
            res2.setText("¡ Incorrecto ! ");
        }
        if(rp1==3){
            res3.setText("¡ Corecto ! ");
        }
        else {
            res3.setText("¡ Incorrecto ! ");
        }
        if(rp1==4){
            res4.setText("¡ Corecto ! ");
        }
        else {
            res4.setText("¡ Incorrecto ! ");
        }
        if(rp1==5){
            res5.setText("¡ Corecto ! ");
        }
        else {
            res5.setText("¡ Incorrecto ! ");
        }

        int suma = rp1+rp2+rp3+rp4+rp5;
        total.setText(suma+"/5");


    }
}

