package com.example.aplicacionbilletera;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class GraficoGastosActivity extends AppCompatActivity {

    private GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_gastos);

        graphView = findViewById(R.id.graphViewGastos);

        ArrayList<Registro> listaRegistros = MainActivity.getListaRegistros();

        // Procesar los datos de la lista de registros para el gráfico
        DataPoint[] dataPoints = processData(listaRegistros);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
        graphView.addSeries(series);
    }

    private DataPoint[] processData(ArrayList<Registro> listaRegistros) {
        DataPoint[] dataPoints = new DataPoint[listaRegistros.size()];

        for (int i = 0; i < listaRegistros.size(); i++) {
            Registro registro = listaRegistros.get(i);
            long x = registro.getFecha();  // Obtener la fecha en milisegundos
            double y = registro.getMonto();

            dataPoints[i] = new DataPoint(x, y);
        }

        return dataPoints;
    }
}


