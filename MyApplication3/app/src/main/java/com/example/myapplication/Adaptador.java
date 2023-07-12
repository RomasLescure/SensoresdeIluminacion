package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Adaptador extends BaseAdapter {
    private Context contexto;
    private List<Datos> ListaObjetos;

    public Adaptador(Context contexto, List<Datos> listaObjetos) {
        this.contexto = contexto;
        ListaObjetos = listaObjetos;
    }

    public int getCount() {
        return ListaObjetos.size();
    }

    @Override
    public Object getItem(int position) {
        return ListaObjetos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista = convertView;
        LayoutInflater inflate = LayoutInflater.from(contexto);
        vista = inflate.inflate(R.layout.layout, null);

        TextView titulo = vista.findViewById(R.id.titulo);
        TextView detalle = vista.findViewById(R.id.pregunta);
        titulo.setText(ListaObjetos.get(position).getTitulo());
        detalle.setText(ListaObjetos.get(position).getDetalle());

        RadioButton r1 = vista.findViewById(R.id.r1);
        r1.setText(ListaObjetos.get(position).getRB1());

        RadioButton r2 = vista.findViewById(R.id.r2);
        r2.setText(ListaObjetos.get(position).getRB2());

        RadioButton r3 = vista.findViewById(R.id.r3);
        r3.setText(ListaObjetos.get(position).getRB3());

        RadioButton r4 = vista.findViewById(R.id.r4);
        r4.setText(ListaObjetos.get(position).getRB4());

        View finalVista = vista;

        Button buttonEnviar = finalVista.getRootView().findViewById(R.id.button2);

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rp1 = 0;
                int rp2 = 0;
                int rp3 = 0;
                int rp4 = 0;
                int rp5 = 0;

                try {
                    if (ListaObjetos.get(position).getId() == 1) {
                        if (r1.isChecked()) {
                            rp1 = 1;
                        } else if (r2.isChecked()) {
                            rp1 = 0;
                        } else if (r3.isChecked()) {
                            rp1 = 0;
                        } else if (r4.isChecked()) {
                            rp1 = 0;
                        } else {
                            // Ningún RadioButton seleccionado
                            throw new Exception("Debes seleccionar una opción");
                        }
                    } else if (ListaObjetos.get(position).getId() == 2) {
                        if (r1.isChecked()) {
                            rp2 = 0;
                        } else if (r2.isChecked()) {
                            rp2 = 0;
                        } else if (r3.isChecked()) {
                            rp2 = 1;
                        } else if (r4.isChecked()) {
                            rp2 = 0;
                        } else {
                            // Ningún RadioButton seleccionado
                            throw new Exception("Debes seleccionar una opción");
                        }
                    } else if (ListaObjetos.get(position).getId() == 3) {
                        if (r1.isChecked()) {
                            rp3 = 1;
                        } else if (r2.isChecked()) {
                            rp3 = 0;
                        } else if (r3.isChecked()) {
                            rp3 = 0;
                        } else if (r4.isChecked()) {
                            rp3 = 0;
                        } else {
                            // Ningún RadioButton seleccionado
                            throw new Exception("Debes seleccionar una opción");
                        }
                    } else if (ListaObjetos.get(position).getId() == 4) {
                        if (r1.isChecked()) {
                            rp4 = 0;
                        } else if (r2.isChecked()) {
                            rp4 = 1;
                        } else if (r3.isChecked()) {
                            rp4 = 0;
                        } else if (r4.isChecked()) {
                            rp4 = 0;
                        } else {
                            // Ningún RadioButton seleccionado
                            throw new Exception("Debes seleccionar una opción");
                        }
                    } else if (ListaObjetos.get(position).getId() == 5) {
                        if (r1.isChecked()) {
                            rp5 = 0;
                        } else if (r2.isChecked()) {
                            rp5 = 0;
                        } else if (r3.isChecked()) {
                            rp5 = 1;
                        } else if (r4.isChecked()) {
                            rp5 = 0;
                        } else {
                            // Ningún RadioButton seleccionado
                            throw new Exception("Debes seleccionar una opción");
                        }
                    }

                    // Enviar el valor rp utilizando un Intent
                    Intent intent = new Intent(contexto, resultado.class);
                    intent.putExtra("rp1", rp1);
                    intent.putExtra("rp2", rp2);
                    intent.putExtra("rp3", rp3);
                    intent.putExtra("rp4", rp4);
                    intent.putExtra("rp5", rp5);
                    contexto.startActivity(intent);

                } catch (Exception e) {
                    Toast.makeText(contexto, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return vista;
    }
}
