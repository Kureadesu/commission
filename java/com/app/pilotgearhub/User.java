package com.app.pilotgearhub;

import com.google.firebase.firestore.FirebaseFirestore;

public class User {
    private String first_name;
    private String last_name;
    private String email;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final String COLLECTION_NAME = "users";

    public User() {
        // Default constructor required for Firestore
    }

    public User(String first_name, String last_name, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }
}