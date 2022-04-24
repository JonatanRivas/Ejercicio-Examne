package com.aplicacion.ejercicio_14;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tablas.Picture;

public class Adaptador extends BaseAdapter {

    private Context context;
    private ArrayList<Picture> listItem;

    public Adaptador(Context context, ArrayList<Picture> listItem) {
        this.context = context;
        this.listItem = listItem;
    }

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int i) {
        return listItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Picture item = (Picture) getItem(i);

        view = LayoutInflater.from(context).inflate(R.layout.item, null);
        

        ImageView imageView = view.findViewById(R.id.itemFoto);
        TextView editText = view.findViewById(R.id.itemTitulo);

        Bitmap image = BitmapFactory.decodeFile(item.getPathImage());

        imageView.setImageBitmap(image);
        editText.setText(item.getName());



        return view;
    }
}
