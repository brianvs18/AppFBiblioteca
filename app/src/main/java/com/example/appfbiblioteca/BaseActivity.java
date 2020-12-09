package com.example.appfbiblioteca;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appfbiblioteca.adapters.LibrosAdapter;
import com.example.appfbiblioteca.connection.FirebaseConnection;
import com.example.appfbiblioteca.models.LibrosModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity {

    protected LibrosModel model;
    protected ArrayList<LibrosModel> modelArrayList;
    protected LibrosAdapter adapter;

    protected FirebaseFirestore db;
    protected FirebaseAuth mAuth;
    protected FirebaseStorage firebaseStorage;
    //protected StorageReference mStorageRef;

    protected Query query;
    protected CollectionReference collectionReference;
    protected StorageReference storageReference, fileReference;

    protected final String COLLECTION_NAME = "libros";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected void init(){
        model = new LibrosModel();
        db = FirebaseConnection.ConnectionFirestore();
        mAuth = FirebaseConnection.ConnectionAuth();
        firebaseStorage = FirebaseConnection.ConnectionStorage();
        collectionReference = db.collection(COLLECTION_NAME);
    }

    protected void makeSimpleToast(String message, int duration){
        Toast.makeText(this, message, duration).show();
    }

    protected void makeSimpleAlertDialog(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    protected void goToList(){
        Intent intent = new Intent(this, com.example.appfbiblioteca.ListActivity.class);
        startActivity(intent);
    }
    protected void goToCreate(){
        Intent intent = new Intent(this, com.example.appfbiblioteca.CreateActivity.class);
        startActivity(intent);
    }
    protected void goToEdit(LibrosModel model){
        Intent intent = new Intent(this, com.example.appfbiblioteca.EditActivity.class);
        intent.putExtra("model", model);
        startActivity(intent);
    }
    protected void goToSearch(){
        Intent intent = new Intent(this, com.example.appfbiblioteca.BaseActivity.class);
        startActivity(intent);
    }
    protected void goToDetail(LibrosModel model){
        Intent intent = new Intent(this, com.example.appfbiblioteca.DetailActivity.class);
        intent.putExtra("model", model);
        startActivity(intent);
    }
}