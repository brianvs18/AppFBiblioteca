package com.example.appfbiblioteca;

import android.os.Bundle;

import com.example.appfbiblioteca.models.LibrosModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class DetailActivity extends BaseActivity {

    private FloatingActionButton fab_detail_list, fab_detail_edit, fab_detail_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();

        model = (LibrosModel) getIntent().getSerializableExtra("model");
        if(model != null){
            Bundle bundle = new Bundle();
            bundle.putSerializable("model", model);
            DataDetailFragment.receiveData(bundle);
        }else{
            makeSimpleAlertDialog("Error", "Modelo vacío");
        }

        fab_detail_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToList();
            }
        });

        fab_detail_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model = (LibrosModel) getIntent().getSerializableExtra("model");
                if(model != null){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("model", model);
                }else{
                    makeSimpleAlertDialog("Error", "Modelo vacío");
                }
                goToEdit(model);
            }
        });

        fab_detail_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(view, "Seguro que deseas eliminarlo?", Snackbar.LENGTH_LONG);
                snackbar.setAction("Estoy seguro!", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                snackbar.show();
            }
        });
    }
    protected void init(){
        fab_detail_list = findViewById(R.id.fab_detail_list);
        fab_detail_edit = findViewById(R.id.fab_detail_edit);
        fab_detail_delete  = findViewById(R.id.fab_detail_delete);
    }
}