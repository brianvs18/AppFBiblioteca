package com.example.appfbiblioteca;

import android.os.Bundle;

import com.example.appfbiblioteca.models.LibrosModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class CreateActivity extends BaseActivity {
    FloatingActionButton fab_create_save, fab_create_clear, fab_create_back;
    EditText et_create_nombre, et_create_autor, et_create_editorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();

        fab_create_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToList();
            }
        });

        fab_create_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = null, nombre, autor, editorial;
                boolean active;

                nombre = et_create_nombre.getText().toString();
                autor = et_create_autor.getText().toString();
                editorial = et_create_editorial.getText().toString();

                if(nombre.isEmpty()||autor.isEmpty()||editorial.isEmpty()){
                    makeSimpleAlertDialog("Información","Todos los campos deben estar diligenciados");
                }else{
                    model = new LibrosModel();
                    id = collectionReference.document().getId();
                    model.setActive(true);
                    model.setId(id);
                    model.setNombre(nombre);
                    model.setAutor(autor);
                    model.setEditorial(editorial);

                    save(model);
                }
            }
        });

        fab_create_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });
    }

    private void save(LibrosModel model) {
        if(collectionReference != null){
            collectionReference.add(model)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if(task.isSuccessful()){
                                if(task.getResult()!= null){
                                    makeSimpleAlertDialog("Exitoso","El libro fue guardado correctamente");
                                    clear();
                                }else{
                                    makeSimpleAlertDialog("Advertencia","El libro no fue guardado correctamente");
                                }
                            }else{
                                makeSimpleAlertDialog("Error", task.getException().getMessage());
                            }
                        }
                    });
        }else{
            makeSimpleAlertDialog("Error","No hay conexión con la base de datos");
        }
    }


    protected void init(){
        fab_create_save = findViewById(R.id.fab_create_save);
        fab_create_clear = findViewById(R.id.fab_create_clear);
        fab_create_back = findViewById(R.id.fab_create_back);
        et_create_nombre = findViewById(R.id.et_create_nombre);
        et_create_autor = findViewById(R.id.et_create_autor);
        et_create_editorial = findViewById(R.id.et_create_editorial);
    }

    private void clear(){
        et_create_nombre.setText("");
        et_create_autor.setText("");
        et_create_editorial.setText("");

        et_create_nombre.requestFocus();
    }
}