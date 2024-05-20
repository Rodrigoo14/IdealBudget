package com.example.idealbudget;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RegistroAdapter extends ArrayAdapter<Registro> {

    private Context context;
    private ArrayList<Registro> listaRegistros;

    public RegistroAdapter(Context context, ArrayList<Registro> listaRegistros) {
        super(context, 0, listaRegistros);
        this.context = context;
        this.listaRegistros = listaRegistros;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_registro, parent, false);
        }

        Registro registro = listaRegistros.get(position);

        TextView txtFecha = convertView.findViewById(R.id.txtFecha);
        TextView txtDescripcion = convertView.findViewById(R.id.txtDescripcion);
        TextView txtMonto = convertView.findViewById(R.id.txtMonto);

        // Configuración para mostrar la fecha
        long fechaLong = registro.getFecha();
        String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date(fechaLong));
        Log.d("Fecha", fecha);  // Para depurar y verificar la fecha
        txtFecha.setText(fecha);  // Asegúrate de que esto esté descomentado y correcto

        txtDescripcion.setText(registro.getDescripcion());
        txtMonto.setText(String.valueOf(registro.getMonto()));

        return convertView;
    }
}




