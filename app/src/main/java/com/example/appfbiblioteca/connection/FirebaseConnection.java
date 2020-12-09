package com.example.appfbiblioteca.connection;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class FirebaseConnection {
    private static FirebaseAuth mAuth;

    //private static StorageReference mStorageRef;
    private static FirebaseStorage firebaseStorage;

    private static FirebaseFirestore db;


    public static FirebaseAuth ConnectionAuth(){
        return mAuth = FirebaseAuth.getInstance();
    }

    public static FirebaseFirestore ConnectionFirestore(){
        return db = FirebaseFirestore.getInstance();
    }

    public static FirebaseStorage ConnectionStorage(){
        return firebaseStorage = FirebaseStorage.getInstance();
    }
}
