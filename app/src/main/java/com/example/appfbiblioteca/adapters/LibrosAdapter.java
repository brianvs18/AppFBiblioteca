package com.example.appfbiblioteca.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appfbiblioteca.R;
import com.example.appfbiblioteca.models.LibrosModel;

import java.util.ArrayList;

public class LibrosAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<LibrosModel> modelArrayList;

    public LibrosAdapter(Context context, ArrayList<LibrosModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public LibrosModel getItem(int position) {
        return modelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            view = layoutInflater.inflate(R.layout.libros_list_item, viewGroup, false);
        }

        TextView tv_libros_list_item_nombre = view.findViewById(R.id.tv_libros_list_item_nombre);
        TextView tv_libros_list_item_autor = view.findViewById(R.id.tv_libros_list_item_autor);

        tv_libros_list_item_nombre.setText(getItem(position).getNombre());
        tv_libros_list_item_autor.setText(getItem(position).getAutor());

        return view;
    }
}
