package com.aplicacion.ejercicio_14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    
    Button btnMenuTomarFoto, btnMenuVistaFotos, btnMenuVistaTodasFotos;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnMenuTomarFoto = (Button) findViewById(R.id.btbMenuTomarFoto);

        btnMenuVistaFotos = (Button) findViewById(R.id.btbMenuListaFotos);

        btnMenuVistaTodasFotos = (Button) findViewById(R.id.btbMenuListaTodasFoto);


        btnMenuTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityTomarFoto.class);
                startActivity(intent);
            }
        });

        btnMenuVistaFotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityMostrar.class);
                startActivity(intent);
            }
        });

        btnMenuVistaTodasFotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityFotosAlmacenamiento.class);
                startActivity(intent);
            }
        });



    }
}