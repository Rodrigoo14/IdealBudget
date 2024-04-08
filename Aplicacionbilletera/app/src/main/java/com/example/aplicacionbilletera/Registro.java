package com.example.aplicacionbilletera;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Registro {

    private String descripcion;
    private double monto;
    private long fecha;

    public Registro(String descripcion, double monto) {
        this.descripcion = descripcion;
        this.monto = monto;
        this.fecha = obtenerFechaActual();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public long getFecha() {
        return fecha;
    }

    private long obtenerFechaActual() {
        return System.currentTimeMillis();
    }
}


