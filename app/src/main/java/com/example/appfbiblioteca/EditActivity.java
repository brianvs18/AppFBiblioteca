package com.example.appfbiblioteca;

import android.os.Bundle;

import com.example.appfbiblioteca.models.LibrosModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;

public class EditActivity extends BaseActivity {
    private static String id, nombre, autor, editorial;

    EditText et_edit_nombre, et_edit_autor, et_edit_editorial;
    FloatingActionButton  fab_edit_save;

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

        fab_edit_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id, nombre, autor, editorial;

                nombre = et_edit_nombre.getText().toString();
                autor = et_edit_autor.getText().toString();
                editorial = et_edit_editorial.getText().toString();

                if(nombre.isEmpty() || autor.isEmpty() || editorial.isEmpty()){
                    makeSimpleAlertDialog("informacion", "Por favor llene todos los campos");
                }else{
                    model = new LibrosModel();
                    model.setNombre(nombre);
                    model.setAutor(autor);
                    model.setEditorial(editorial);

                    updateL(model);
                    }
                }
        });
    }

    private void updateL(LibrosModel model) {
        if(collectionReference != null){
            collectionReference.document().update()
                    .addOnCompleteListener(new  OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if(task.isSuccessful()){
                                if(task.getResult()!= null){
                                    makeSimpleAlertDialog("Exitoso","El libro fue actualizado correctamente");
                                    clear();
                                }else{
                                    makeSimpleAlertDialog("Advertencia","El libro no fue actualizado correctamente");
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
        et_edit_nombre = findViewById(R.id.et_edit_nombre);
        et_edit_autor = findViewById(R.id.et_edit_autor);
        et_edit_editorial = findViewById(R.id.et_edit_editorial);
        fab_edit_save = findViewById(R.id.fab_edit_save);
    }

    private void clear(){
        et_edit_nombre.setText("");
        et_edit_autor.setText("");
        et_edit_editorial.setText("");

        et_edit_nombre.requestFocus();
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