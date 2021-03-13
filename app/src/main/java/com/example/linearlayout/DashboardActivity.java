package com.example.linearlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity {

    Button btn_logout;
    Preferences preferences;
    TextView tv_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        preferences = new Preferences();

        btn_logout = findViewById(R.id.btn_logout_dashboard);
        tv_user = findViewById(R.id.tv_welcomeTitle_dashboard);

        if (!preferences.getStatus(getApplicationContext())) {
            Intent intent = new Intent(getApplicationContext(), LandingPage.class);
            startActivity(intent);
            finish();
        } else {
            tv_user.setText("Welcome, " + preferences.getEmail(getApplicationContext()));
        }

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                preferences.setStatus(getApplicationContext(), false);
                checkUser();
            }
        });
    }

    private void checkUser() {
        if (!preferences.getStatus(getApplicationContext())) {
            Intent intent = new Intent(getApplicationContext(), LandingPage.class);
            startActivity(intent);
            finish();
        }
    }
}