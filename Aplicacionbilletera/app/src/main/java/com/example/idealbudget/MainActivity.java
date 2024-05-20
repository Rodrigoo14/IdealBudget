package com.example.idealbudget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.idealbudget.db.DbHelper;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Definimos el onClickListener del botón de inicio de sesión
        findViewById(R.id.btnIngresar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores ingresados por el usuario
                String cui = ((EditText) findViewById(R.id.txtLoginCui)).getText().toString();
                String contra = ((EditText) findViewById(R.id.txtLoginContra)).getText().toString();

                // Verificar en la base de datos si el CUI y la contraseña coinciden
                DbHelper dbHelper = new DbHelper(MainActivity.this);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_USUARIOS +
                        " WHERE cui = ? AND contra = ?", new String[]{cui, contra});

                // Comprobar si se encontró un usuario con el CUI y la contraseña proporcionados
                if (cursor.moveToFirst()) {
                    // Usuario autenticado, iniciar la actividad de bienvenida
                    Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                    finish(); // Cierra la actividad actual
                } else {
                    // No se encontró un usuario con el CUI y la contraseña proporcionados
                    Toast.makeText(MainActivity.this, "CUI o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }

                // Cerrar la base de datos y el cursor
                cursor.close();
                db.close();
            }
        });

        // Definimos el onClickListener para el texto "Crear cuenta"
        findViewById(R.id.createAccountTextView1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creamos un Intent para abrir la actividad de creación de cuenta
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);

                startActivity(intent);
            }
        });
    }
}
