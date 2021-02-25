package com.example.linearlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText txt_email, txt_password;
    TextInputLayout layout_email, layout_password;
    ImageView img_backButton, img_infoButton, img_arrowButton;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);

        txt_email = findViewById(R.id.txt_email_login);
        txt_password = findViewById(R.id.txt_password_login);
        img_backButton = findViewById(R.id.img_backButton_login);
        img_infoButton = findViewById(R.id.img_infoButton_login);
        img_arrowButton = findViewById(R.id.img_arrowButton_login);
        layout_email = findViewById(R.id.layout_email_login);
        layout_password = findViewById(R.id.layout_password_login);
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
            startActivity(intent);
            finish();
        }

        img_backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        img_arrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void loginUser() {
        String email = txt_email.getText().toString();
        String password = txt_password.getText().toString();
        if (password.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            layout_email.setError("Invalid email");
            layout_password.setError("Invalid password");
        } else if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.isEmpty()) {
            layout_password.setError("Invalid password");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            layout_email.setError("Invalid email");
        } else if (password.length() < 6) {
            layout_password.setError("Password must be at least 6 characters");
        } else {
            layout_email.setError(null);
            layout_password.setError(null);
            progressDialog.setMessage("Signing you in...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
//                                preferences.setStatus(getApplicationContext(), true);
                                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}