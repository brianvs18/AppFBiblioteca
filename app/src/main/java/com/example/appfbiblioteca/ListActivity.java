package com.example.appfbiblioteca;

import android.os.Bundle;

import com.example.appfbiblioteca.adapters.LibrosAdapter;
import com.example.appfbiblioteca.models.LibrosModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends BaseActivity {

    private FloatingActionButton fab_list_create;
    private ListView lv_list_libros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();

        fab_list_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCreate();
            }
        });

        lv_list_libros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                model = modelArrayList.get(position);
                goToDetail(model);
            }
        });
    }

    protected void init(){
        fab_list_create = findViewById(R.id.fab_list_create);
        lv_list_libros = findViewById(R.id.lv_list_libros);
    }

    protected void getLibros(){
        if(collectionReference != null){
            collectionReference.get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                if(task.getResult() != null){
                                    modelArrayList = new ArrayList<>();
                                    for(QueryDocumentSnapshot snapshot : task.getResult()){
                                        model = snapshot.toObject(LibrosModel.class);
                                        modelArrayList.add(model);
                                    }
                                    if(modelArrayList.size()>0){
                                        paintLibros(modelArrayList);
                                    }else{
                                        makeSimpleAlertDialog("Alerta","No hay elementos");
                                    }
                                }else{
                                    makeSimpleAlertDialog("Advertencia","No hay elementos");
                                }
                            }else{
                                makeSimpleAlertDialog("Error", task.getException().getMessage());
                            }
                        }
                    });
        }else{
            makeSimpleToast("Error en base de datos", 1);
        }

    }

    private void paintLibros(ArrayList<LibrosModel> modelArrayList) {
        adapter = new LibrosAdapter(this,modelArrayList);
        lv_list_libros.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLibros();
    }
}