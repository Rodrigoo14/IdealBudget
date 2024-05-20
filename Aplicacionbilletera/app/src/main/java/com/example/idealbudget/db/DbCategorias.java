package com.example.idealbudget.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbCategorias extends DbHelper {
    Context context;

    public DbCategorias(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarCategoria(String descripcion) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("descripcion", descripcion);

            id = db.insert(TABLE_CATEGORIAS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }
}
