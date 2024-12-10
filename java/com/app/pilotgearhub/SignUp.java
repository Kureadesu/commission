package com.app.pilotgearhub;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;

import java.util.UUID;

public class SignUp extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText fname, lname, email, password, mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        FirebaseApp.initializeApp(this);

        // variables
        Button register = findViewById(R.id.btn_register);
        register.setOnClickListener(v -> regOnClick());
        TextView login = findViewById(R.id.btn_loginForm1);
        login.setOnClickListener(v -> backToLogin());

        fname = findViewById(R.id.edt_Fname);
        lname = findViewById(R.id.edt_Lname);
        email = findViewById(R.id.edt_Email);
        password = findViewById(R.id.edt_Pass);
        mobile = findViewById(R.id.edt_MobileNum);
    }

    // methods for each function
    @SuppressLint("RestrictedApi")
    public void regOnClick() {

        String originalEmail = email.getText().toString();
        String modifiedEmail = originalEmail.replaceAll("\\.{2,}", ".");

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(modifiedEmail, password.toString())
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        // Store additional user data in Firestore
                        UUID uuid = UUID.randomUUID();
                        String userId = uuid.toString()
                                .replaceAll("[^a-zA-Z0-9]", "")
                                .substring(0, 20); // Limit to 20 characters
                        Map<String, Object> userData = new HashMap<>();
                        userData.put("first_name", fname.getText().toString());
                        userData.put("last_name", lname.getText().toString());
                        userData.put("email", originalEmail);
                        userData.put("mobile_number", mobile.getText().toString());
                        userData.put("password", password.getText().toString());

                        db.collection("users").document(userId) // Use userId as document ID
                                .set(userData)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(this, "Successfully registered", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(this, MainActivity.class));
                                    finish();
                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(this, "Failed to store user data", Toast.LENGTH_SHORT).show();
                                    // Handle error (e.g., delete the Firebase user account)
                                });
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void backToLogin() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}