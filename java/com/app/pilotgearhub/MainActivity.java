package com.app.pilotgearhub;

import static android.content.ContentValues.TAG;
import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private EditText email, password;
    private DocumentReference db;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        firestore = FirebaseFirestore.getInstance();

        // declare variables
        Button loginButton = findViewById(R.id.btn_login);
        TextView signUpButton = findViewById(R.id.btn_signUp);
        TextView resetPassword = findViewById(R.id.btn_resetPass);

        // set on click listeners for each button
        loginButton.setOnClickListener(v -> login());
        signUpButton.setOnClickListener(v -> signUp());
        resetPassword.setOnClickListener(v -> resetPassword());

    }

    // declare methods for each function
    public void login() {
        email = findViewById(R.id.edt_email);
        password = findViewById(R.id.edt_password);

        String originalEmail = email.getText().toString().trim();
        String passwordString = password.getText().toString().trim();

        if (originalEmail.isEmpty()) {
            email.setError("Email is required");
            return;
        }

        if (passwordString.isEmpty()) {
            password.setError("Password is required");
            return;
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(originalEmail, passwordString)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithEmail:success");

                        // Query Firestore for user data using email
                        firestore.collection("users")
                                .whereEqualTo("email", originalEmail)
                                .get()
                                .addOnCompleteListener(userTask -> {
                                    if (userTask.isSuccessful() && !userTask.getResult().isEmpty()) {
                                        DocumentSnapshot document = userTask.getResult().getDocuments().get(0);
                                        String firstName = document.getString("first_name") != null ? document.getString("first_name") : "Unknown";
                                        String lastName = document.getString("last_name") != null ? document.getString("last_name") : "User";
                                        String fullName = firstName + " " + lastName;

                                        // Pass data to Home activity
                                        Intent intent = new Intent(this, Home.class);
                                        intent.putExtra("name", fullName);
                                        intent.putExtra("email", originalEmail);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Log.w(TAG, "No matching document found.");
                                        Toast.makeText(this, "No matching user found.", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(e -> {
                                    Log.e(TAG, "Error fetching user data: ", e);
                                    Toast.makeText(this, "Error fetching user data.", Toast.LENGTH_SHORT).show();
                                });
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public void signUp() {
        startActivity(new Intent(this, SignUp.class));
        finish();
    }

    public void resetPassword() {
        startActivity(new Intent(this, ResetPass.class));
        finish();
    }

}