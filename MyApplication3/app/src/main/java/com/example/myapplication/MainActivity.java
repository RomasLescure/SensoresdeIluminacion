package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    private ListView listadatos;
    ArrayList<Datos> Lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listadatos=(ListView)findViewById(R.id.lsdatos);
        Lista= new ArrayList<Datos>();
        Lista.add(new Datos(1, "Pregunta #1", "En la caja del seguro social queremos saber si un paciente es jubilado, ¿qué información se pide?  ", "Edad","Año actual ","Medicamentos ","Nombre "));
        Lista.add(new Datos(2, "Pregunta #2", "¿Si la salida de mi calculo es 2, cuáles son las posibles entradas para que la suma de esas entradas coincida con la salida?  ","1 y 100 ","1 y 10","1 y 1 ","2 y 2"));
        Lista.add(new Datos(3, "Pregunta #3", "¿Para saber si el estudiante de la UTP está matriculado, que documento se podría ingresar como entrada de comprobación? ", "Constancia de matricula  ","Ingreso mensual  ","Pasaporte  ","Horario de grupo  "));
        Lista.add(new Datos(4, "Pregunta #4", "¿Si se desea ingresar al sistema de matrícula de la UTP, cuál sería la entrada para poder acceder al sistema?  ", "Token ","Cedula y contraseña  ","Correo electrónico y contraseña  ","Nombre y contraseña "));
        Lista.add(new Datos(5, "Pregunta #5", "Si tengo un algoritmo que lee dos cantidades y los suman, e ingreso 1 y 2, cual seria la salida?", "1","2","3","4"));
        //"1","2","3","4"
        Adaptador miadaptador = new Adaptador(getApplicationContext(), Lista);
        listadatos.setAdapter(miadaptador);

    }
}
