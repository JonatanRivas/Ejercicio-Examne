package com.aplicacion.ejercicio_14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import configuraciones.SQLiteConexion;
import configuraciones.Transacciones;
import tablas.Picture;

public class ActivityMostrar extends AppCompatActivity {

    SQLiteConexion conexion;

    ListView listViewFotos;

    ArrayList<String> arrayListStringPicture;

    ArrayList<Picture> arrayListPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);


        conexion = new SQLiteConexion(this, Transacciones.NAME_DATABASE, null, 1);

        listViewFotos = (ListView) findViewById(R.id.listViewFotos);

        obtenerPictures();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayListStringPicture);

        listViewFotos.setAdapter(adp);


        listViewFotos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                mostrarPicture(i);
            }
        });

    }

    private void mostrarPicture(int i){

        Picture picture = arrayListPicture.get(i);

        Bundle bundle = new Bundle();
        bundle.putSerializable("picture", picture);

        Intent intent = new Intent(getApplicationContext(), ActivityDetallePicture.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    private void obtenerPictures(){

        SQLiteDatabase database = conexion.getReadableDatabase();

        Picture tempPict = null;

        arrayListPicture = new ArrayList<Picture>();

        Cursor cursor = database.rawQuery(Transacciones.SELECT_ALL_TABLE_PICTURE, null);

        while (cursor.moveToNext()){

            tempPict = new Picture();

            tempPict.setId(cursor.getInt(0));
            tempPict.setName(cursor.getString(1));
            tempPict.setDescription(cursor.getString(2));
            tempPict.setPathImage(cursor.getString(3));
            tempPict.setImage(cursor.getBlob(4));

            arrayListPicture.add(tempPict);
        }

        cursor.close();

        llenarListaStringPicture();






    }

    private void llenarListaStringPicture(){

        arrayListStringPicture = new ArrayList<String>();

        for (Picture p: arrayListPicture) {

            arrayListStringPicture.add(
                    p.getId() + " | " +
                    p.getName()+
                    " - "+ p.getDescription()
            );
        }
    }
}