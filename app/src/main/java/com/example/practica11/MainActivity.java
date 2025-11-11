package com.example.practica11;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMostrarContactos = findViewById(R.id.btnMostrarContactos);
        Button btnSeleccionarContacto = findViewById(R.id.btnSeleccionarContacto);
        Button btnNavegador = findViewById(R.id.btnNavegador);
        Button btnLlamarTelefono = findViewById(R.id.btnLlamarTelefono);
        Button btnMarcarTelefono = findViewById(R.id.btnMarcarTelefono);

        // Mostrar contactos
        btnMostrarContactos.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
            startActivity(intent);
        });

        // Seleccionar contacto
        btnSeleccionarContacto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts/people/"));
            startActivity(intent);
        });

        // Abrir navegador
        btnNavegador.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.es"));
            startActivity(intent);
        });

        // Llamar por teléfono (abre el marcador con número)
        btnLlamarTelefono.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:123456789"));
            startActivity(intent);
        });

        // Marcar número de teléfono (abre el marcador vacío)
        btnMarcarTelefono.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            startActivity(intent);
        });
    }
}
