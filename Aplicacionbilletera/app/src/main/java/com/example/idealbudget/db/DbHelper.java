package com.example.idealbudget.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "idealbudget.db";
    public static final String TABLE_USUARIOS = "usuarios";
    public static final String TABLE_TRANSACCIONES = "transacciones";
    public static final String TABLE_CATEGORIAS = "categorias";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USUARIOS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cui TEXT NOT NULL," +
                "nombres TEXT NOT NULL," +
                "apellidos TEXT NOT NULL," +
                "correo TEXT NOT NULL," +
                "contra TEXT NOT NULL," +
                "saldo REAL)");

        db.execSQL("CREATE TABLE " + TABLE_CATEGORIAS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLE_TRANSACCIONES + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "monto REAL NOT NULL," +
                "tipo TEXT NOT NULL," +
                "descripcion TEXT NOT NULL," +
                "id_categoria INTEGER," +
                "FOREIGN KEY(id_categoria) REFERENCES " + TABLE_CATEGORIAS + "(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACCIONES);
        onCreate(db);
    }
}
