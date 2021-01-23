package com.example.zerowaste;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class User {
    private String fname;
    private String lname;
    private String email;
    private String mobile;

    private CollectionReference collRef = FirebaseFirestore.getInstance().collection("users/");
    public User(){}

}
