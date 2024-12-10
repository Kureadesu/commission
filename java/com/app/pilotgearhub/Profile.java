package com.app.pilotgearhub;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Profile extends AppCompatActivity {

    private FirebaseFirestore db;
    private TextView email;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private Button deleteBtn;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        email = findViewById(R.id.EmailUser);
        Button backBtn = findViewById(R.id.button2);
        Button logOut = findViewById(R.id.button4);

        // Set button click listeners
        backBtn.setOnClickListener(v -> goBack());
        logOut.setOnClickListener(v -> logout());

        // Initialize Firebase
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String userEmail = user.getEmail(); // Get the user's email from FirebaseAuth
            email.setText(userEmail);          // Set the email TextView with the user's email
            fetchUserData(user.getUid());      // Fetch additional data using Firestore
            Log.d("ProfileActivity", "User UID: " + user.getUid());
        } else {
            Log.d("ProfileActivity", "No user is logged in.");
        }

        deleteBtn = findViewById(R.id.deleteAccBtn);
        deleteBtn.setOnClickListener(v -> showDeleteConfirmationDialog());
    }

    private void fetchUserData(String userID) {
        // Assume the user's data is stored in a collection named "users"
        Log.d("ProfileActivity", "Fetching data for userID: " + userID);
        db.collection("users").document(userID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                // Update TextViews
                                // username.setText(getIntent().getStringExtra("name")); // Set full name to username TextView
                                email.setText(document.getString("email")); // Set email to email TextView
                            } else {
                                Log.d("ProfileActivity", "No such document exists!");
                            }
                        } else {
                            Log.d("ProfileActivity", "Task failed: ", task.getException());
                        }
                    }
                })
                .addOnFailureListener(e -> Log.d("ProfileActivity", "Error getting document: ", e));
    }

    /**
     * Show a confirmation dialog before deleting the account.
     */
    private void showDeleteConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Delete Account")
                .setMessage("Are you sure you want to delete your account? This action cannot be undone.")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteAccount();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    /**
     * Deletes the current user's account.
     */
    private void deleteAccount() {
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            String userId = user.getUid();

            // Delete user data from Firestore
            db.collection("users").document(userId)
                    .delete()
                    .addOnSuccessListener(aVoid -> {
                        Log.d("MainActivity", "User data deleted from Firestore.");

                        // Delete the user from Firebase Authentication
                        user.delete()
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        Log.d("MainActivity", "User account deleted from Firebase Authentication.");
                                        Toast.makeText(this, "Account deleted successfully.", Toast.LENGTH_SHORT).show();

                                        // Redirect to login or signup screen
                                        startActivity(new Intent(this, MainActivity.class));
                                        finish();
                                    } else {
                                        Log.e("MainActivity", "Failed to delete user account.", task.getException());
                                        Toast.makeText(this, "Failed to delete account. Please try again.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    })
                    .addOnFailureListener(e -> {
                        Log.e("MainActivity", "Failed to delete user data from Firestore.", e);
                        Toast.makeText(this, "Failed to delete account data. Please try again.", Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(this, "No user logged in.", Toast.LENGTH_SHORT).show();
        }
    }
    // Go back to Home activity
    public void goBack() {
        startActivity(new Intent(this, Home.class));
        finish();
    }

    // Logout user
    public void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
