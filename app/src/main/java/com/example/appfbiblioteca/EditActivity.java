package com.example.appfbiblioteca;

import android.os.Bundle;

import com.example.appfbiblioteca.models.LibrosModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditActivity extends BaseActivity {
    private static String id, nombre, autor, editorial;

    EditText et_edit_nombre, et_edit_autor, et_edit_editorial;
    FloatingActionButton fab_detail_edit, fab_detail_delete, fab_detail_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = findViewById(R.id.toolbar_edit);
        setSupportActionBar(toolbar);

        super.init();
        init();

        model = (LibrosModel) getIntent().getSerializableExtra("model");
        if(model != null){
            Bundle bundle = new Bundle();
            bundle.putSerializable("model", model);

            receiveDataEdit(bundle);

            et_edit_nombre.setText(nombre);
            et_edit_autor.setText(autor);
            et_edit_editorial.setText(editorial);

        }else{
            makeSimpleAlertDialog("Error", "Modelo vacío");
        }
    }

    protected void init(){
        fab_detail_edit = findViewById(R.id.fab_detail_edit);
        fab_detail_delete = findViewById(R.id.fab_detail_delete);
        fab_detail_list = findViewById(R.id.fab_detail_list);
        et_edit_nombre = findViewById(R.id.et_edit_nombre);
        et_edit_autor = findViewById(R.id.et_edit_autor);
        et_edit_editorial = findViewById(R.id.et_edit_editorial);
    }

    static void receiveDataEdit(Bundle bundle){
        LibrosModel model = (LibrosModel) bundle.getSerializable("model");
        if(model != null){
            id = model.getId();
            nombre = model.getNombre();
            autor = model.getAutor();
            editorial = model.getEditorial();
        }
    }
}