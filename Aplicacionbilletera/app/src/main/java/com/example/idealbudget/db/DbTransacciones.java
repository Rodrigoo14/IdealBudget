package com.example.idealbudget.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbTransacciones extends DbHelper {
    Context context;

    public DbTransacciones(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarTransaccion(float monto, String tipo, String descripcion, int id_categoria, String cui) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            // Verificar si el CUI ya existe en la tabla de transacciones
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TRANSACCIONES + " WHERE cui = ?", new String[]{cui});

            if (cursor.moveToFirst()) {
                // CUI ya existe, mostrar un mensaje de error
                Toast.makeText(context, "El CUI ya existe, por favor use un CUI diferente.", Toast.LENGTH_SHORT).show();
            } else {
                // Insertar nueva transacción
                ContentValues values = new ContentValues();
                values.put("monto", monto);
                values.put("tipo", tipo);
                values.put("descripcion", descripcion);
                values.put("id_categoria", id_categoria);
                values.put("cui", cui); // Asegúrate de que la columna 'cui' exista en la tabla

                id = db.insert(TABLE_TRANSACCIONES, null, values);
            }

            cursor.close();
            db.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }

}
