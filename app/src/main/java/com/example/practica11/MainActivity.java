package com.example.practica11;

import android.Manifest; // Necesario para CALL_PHONE

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CALL_PHONE = 100;

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

        // Llamar por teléfono (abre marcador)
        btnLlamarTelefono.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:123456789"));
            startActivity(intent);
        });

        // Marcar número automáticamente (requiere permiso CALL_PHONE)
        btnMarcarTelefono.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
            } else {
                llamarDirectamente();
            }
        });
    }

    private void llamarDirectamente() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:123456789"));
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PHONE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                llamarDirectamente();
            } else {
                Toast.makeText(this, "Permiso de llamada denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}