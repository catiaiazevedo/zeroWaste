package com.example.zerowaste;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class User {
    private int id =1;
    private String fname;
    private String lname;
    private String email;
    private String mobile;

    public User(){}
    public User(String fname, String email){
        this.fname = fname;
        this.email = email;
    }
    public User(int id, String fname, String lname, String email){
        this.id +=1;
        this.fname = fname;
        this.lname = lname;
        this.email = email;

    }
    public int getId(){return id;}

    public String getEmail() { return email; }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
