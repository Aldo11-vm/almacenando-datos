package com.example.application5;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText txtNombre, txtTelefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtNombre= findViewById(R.id.txtNombre);
        txtTelefono= findViewById(R.id.txtTelefono);
    }
    public void guardar(View v)
    {
        if(txtNombre.getText().toString().equals(""))
        {
            Toast.makeText(this, "El nombre no puede estar vacio", Toast.LENGTH_SHORT).show();
            return;
        }
        if(txtTelefono.getText().toString().equals(""))
        {
            Toast.makeText(this, "El telefono no puede estar vacio", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences preferences = getSharedPreferences("contactos", Context.MODE_PRIVATE);
        SharedPreferences.Editor agenda =  preferences.edit();

        agenda.putString(txtNombre.getText().toString(), txtTelefono.getText().toString());
        agenda.commit();
        Toast.makeText(this,"Registro guardado", Toast.LENGTH_SHORT).show();
        txtTelefono.setText("");
        txtNombre.setText("");
    }
    public void buscar(View v)
    {
        String nombre = txtNombre.getText().toString().trim();

        if (nombre.equals(""))
        {
            Toast.makeText(this, "Escribe el nombre del contacto que quieres buscar", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences preferences = getSharedPreferences("contactos", Context.MODE_PRIVATE);
        String telefono = preferences.getString(nombre, null);

        if (telefono != null)
        {
            txtTelefono.setText(telefono);
            Toast.makeText(this, "Se encontro", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "No se encontro", Toast.LENGTH_SHORT).show();
            txtTelefono.setText("");
        }
    }

}
