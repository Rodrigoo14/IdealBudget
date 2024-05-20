package com.example.idealbudget;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Expenses extends AppCompatActivity {

    private EditText edtDescripcion, edtMonto;
    private Button btnAgregar, btnVerGrafico;
    private ListView listView;
    private RegistroAdapter registroAdapter;
    private static ArrayList<Registro> listaRegistros = new ArrayList<>();

        // Método estático para obtener la lista de registro


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtDescripcion = findViewById(R.id.edtDescripcion);
        edtMonto = findViewById(R.id.edtMonto);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnVerGrafico = findViewById(R.id.btnVerGrafico);
        listView = findViewById(R.id.listView);

        listaRegistros = new ArrayList<>();
        registroAdapter = new RegistroAdapter(this, listaRegistros);
        listView.setAdapter(registroAdapter);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarRegistro();
            }
        });

    }

    public static ArrayList<Registro> getListaRegistros() {
        return listaRegistros;
    }

    private void agregarRegistro() {
        String descripcion = edtDescripcion.getText().toString().trim();
        String montoStr = edtMonto.getText().toString().trim();

        if (descripcion.isEmpty() || montoStr.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        double monto = Double.parseDouble(montoStr);

        Registro registro = new Registro(descripcion, monto);
        listaRegistros.add(registro);
        registroAdapter.notifyDataSetChanged();

        // Limpiar campos
        edtDescripcion.setText("");
        edtMonto.setText("");
    }
}