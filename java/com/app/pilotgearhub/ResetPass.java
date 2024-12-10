package com.app.pilotgearhub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class ResetPass extends AppCompatActivity {

    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reset_pass);

        Button resetPass = findViewById(R.id.btn_resetPass);
        resetPass.setOnClickListener(v -> resetPassOnClick());
        TextView backLogin = findViewById(R.id.btn_loginForm2);
        backLogin.setOnClickListener(v -> backToLogin());
        email = findViewById(R.id.edt_email_resetPass);

    }

    public void resetPassOnClick() {

        String emailAddress = email.getText().toString().trim();

        if (!emailAddress.isEmpty()) {
            FirebaseAuth.getInstance().sendPasswordResetEmail(emailAddress)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Update Firestore when reset email is sent
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            String userId = db.collection("users").document().getId(); // Replace with logic to get userId

                            db.collection("users").document(userId)
                                    .update("passwordResetEmailSent", true, "resetRequestTime", FieldValue.serverTimestamp())
                                    .addOnSuccessListener(aVoid -> Log.d("FirestoreUpdate", "Reset email flag updated"))
                                    .addOnFailureListener(e -> Log.e("FirestoreUpdate", "Failed to update reset email flag", e));

                            Toast.makeText(this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                        } else {
                            // Handle errors
                            Log.e("ResetPassword", "Error sending reset email", task.getException());
                            Toast.makeText(this, "Error sending reset email", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            // Email field is empty
            email.setError("Please enter your email");
        }
    }
    public void backToLogin() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}