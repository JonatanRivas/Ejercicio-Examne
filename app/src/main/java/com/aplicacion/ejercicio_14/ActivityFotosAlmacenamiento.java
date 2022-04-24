package com.aplicacion.ejercicio_14;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

import tablas.Picture;

public class ActivityFotosAlmacenamiento extends AppCompatActivity {

    private ArrayList<String> listaImg;

    ListView listView;

    Adaptador adaptador;

    ArrayList<Picture> arrayList;

    Picture pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos_almacenamiento);

        arrayList = new ArrayList<Picture>();


        pic = null;

        listaImg = new ArrayList<String>();
        File path = (getExternalFilesDir(Environment.DIRECTORY_PICTURES));
        File[] files = path.listFiles();

        for (File f:files) {

           pic = new Picture();

           pic.setPathImage(f.getPath());

           pic.setName(f.getName());

           arrayList.add(pic);

        }

        adaptador = new Adaptador(this, arrayList);

        listView = (ListView) findViewById(R.id.listViewItem);


        listView.setAdapter(adaptador);




    }
}