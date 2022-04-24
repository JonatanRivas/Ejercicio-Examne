package com.aplicacion.ejercicio_14;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import configuraciones.SQLiteConexion;
import configuraciones.Transacciones;

public class ActivityTomarFoto extends AppCompatActivity {

    ImageView objImagenView;
    Button btnTomarFotos, btnGuardarSqlite;
    String currentPhotoPath;

    static final int PETICCION_ACCESO_CAM = 100;
    static final int TAKE_PIC_REQUEST = 101;

    /*
    *
    * Variables para poder guardar la imagen
    * */

    EditText txtNombre;
    EditText txtDescripcion;
    Bitmap imagenGlobal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomar_foto);

        objImagenView = (ImageView) findViewById(R.id.imageViewTomarFoto);
        btnGuardarSqlite = (Button) findViewById(R.id.btnGuardarSql);
        btnTomarFotos = (Button) findViewById(R.id.btnTomarFoto);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);

        btnTomarFotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permisos();
            }
        });

        btnGuardarSqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                limpiarEntradas();

                agregarPictureSQL();
            }
        });
    }


    private void permisos(){
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PETICCION_ACCESO_CAM);
        }else{

//            tomarFoto();

            dispatchTakePictureIntent();
        }
    }

    private void tomarFoto(){
        Intent takePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(takePhoto.resolveActivity(getPackageManager()) != null){

            startActivityForResult(takePhoto, TAKE_PIC_REQUEST);
        }


    }

    private void agregarPictureSQL(){

        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NAME_DATABASE, null, 1);
        SQLiteDatabase database = conexion.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Transacciones.PICTURE_NAME, txtNombre.getText().toString());
        values.put(Transacciones.PICTURE_DESCRIPTION, txtDescripcion.getText().toString());
        values.put(Transacciones.PICTURE_PATH_IMAGE, currentPhotoPath);


        ByteArrayOutputStream baos = new ByteArrayOutputStream(20480);

        imagenGlobal.compress(Bitmap.CompressFormat.JPEG, 0 , baos);

        byte[] blob = baos.toByteArray();

        values.put(Transacciones.PICTURE_IMAGE, blob);




        Long result = database.insert(Transacciones.TABLE_PICTURE, Transacciones.PICTURE_ID, values);

        Toast.makeText(getApplicationContext(), "Registro exitoso " + result.toString()
                ,Toast.LENGTH_LONG).show();

        database.close();

        limpiarEntradas();
    }

    private void limpiarEntradas(){
        txtNombre.setText("");
        txtDescripcion.setText("");

        objImagenView.setImageBitmap(null);

        imagenGlobal = null;
    }

    //Para llenar en el almacenamiento
    /**********************************************************************************************************************/

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpeg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;

            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.toString();
            }
            // Continue only if the File was successfully created
            try {
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(this,
                            "com.aplicacion.ejercicio_14.fileprovider",
                            photoFile);

                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                    startActivityForResult(takePictureIntent, TAKE_PIC_REQUEST);
                }
            }catch (Exception e){
                Log.i("Error", "dispatchTakePictureIntent: " + e.toString());
            }
        }
    }

    // Metodos que son override
    /*********************************************************************************************************************************************************/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == PETICCION_ACCESO_CAM){

            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

//                tomarFoto();
                dispatchTakePictureIntent();
            }
        }else{

            Toast.makeText(getApplicationContext(), "La aplicacion nesecita permisos de acceso a camara", Toast.LENGTH_LONG).show();
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == TAKE_PIC_REQUEST && resultCode == RESULT_OK){

//            Bundle bundle = data.getExtras();

            Bitmap image = BitmapFactory.decodeFile(currentPhotoPath);

            imagenGlobal = image;

            objImagenView.setImageBitmap(image);

            Toast.makeText(getApplicationContext(), "Registro de imagen exitoso en almacenamiento "
                    ,Toast.LENGTH_LONG).show();
        }
    }
}