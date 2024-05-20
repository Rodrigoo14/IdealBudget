package com.example.idealbudget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.idealbudget.db.DbUsuarios;

public class CreateAccountActivity extends AppCompatActivity {

    EditText txt_Cui, txt_Nombres, txt_Apellidos, txt_Correo, txt_Contra, txt_RepContra;
    Button btn_Registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Initialize UI elements
        txt_Cui = findViewById(R.id.txtCui);
        txt_Nombres = findViewById(R.id.txtNombres);
        txt_Apellidos = findViewById(R.id.txtApellidos);
        txt_Correo = findViewById(R.id.txtEmail);
        txt_Contra = findViewById(R.id.txtContra);
        txt_RepContra = findViewById(R.id.txtRepContra);
        btn_Registrar = findViewById(R.id.btnRegistrar);

        // Set onClick listener for the registration button
        btn_Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txt_Contra.getText().toString().equals(txt_RepContra.getText().toString())) {
                    Toast.makeText(CreateAccountActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
                } else {
                    DbUsuarios dbUsuarios = new DbUsuarios(CreateAccountActivity.this);
                    long id = dbUsuarios.insertarUsuario(
                            txt_Cui.getText().toString(),
                            txt_Nombres.getText().toString(),
                            txt_Apellidos.getText().toString(),
                            txt_Correo.getText().toString(),
                            txt_Contra.getText().toString()
                    );

                    if (id > 0) {
                        Toast.makeText(CreateAccountActivity.this, "Usuario creado", Toast.LENGTH_LONG).show();
                        // Regresar a MainActivity
                        Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Cierra la actividad actual
                    } else {
                        Toast.makeText(CreateAccountActivity.this, "Error al crear usuario", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
