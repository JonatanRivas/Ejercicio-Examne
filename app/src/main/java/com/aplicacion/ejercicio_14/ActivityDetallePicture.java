package com.aplicacion.ejercicio_14;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.ByteArrayInputStream;

import tablas.Picture;

public class ActivityDetallePicture extends AppCompatActivity {

    ImageView imageViewMostrar, imageViewMostrarPath;

    EditText txtNombre, txtDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_picture);

        imageViewMostrar = (ImageView) findViewById(R.id.imageViewMostrarFoto);
        imageViewMostrarPath = (ImageView) findViewById(R.id.imageViewMostrarFotoPath);

        txtNombre = (EditText) findViewById(R.id.txtMostrarNombre);
        txtDescripcion = (EditText) findViewById(R.id.txtMostrarDescripcion);

        Bundle objEnviado = getIntent().getExtras();

        Picture imagen = null;

        if(objEnviado != null){
            imagen = (Picture) objEnviado.getSerializable("picture");

            txtNombre.setText(imagen.getName());
            txtDescripcion.setText(imagen.getDescription());

            mostrarImagen(imagen.getImage());

            Bitmap image = BitmapFactory.decodeFile(imagen.getPathImage());

            imageViewMostrarPath.setImageBitmap(image);
        }


    }

    private void mostrarImagen(byte[] image) {
        Bitmap bitmap = null;

        ByteArrayInputStream bais = new ByteArrayInputStream(image);
        bitmap = BitmapFactory.decodeStream(bais);

        imageViewMostrar.setImageBitmap(bitmap);
    }
}