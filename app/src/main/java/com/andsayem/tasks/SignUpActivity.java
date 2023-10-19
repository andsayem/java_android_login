package com.andsayem.tasks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.loginui.R;

public class SignUpActivity extends AppCompatActivity {
    TextView HomeTextView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Find the TextView with the ID signupText
        HomeTextView = findViewById(R.id.HomeTextView);

        // Set an OnClickListener for the signupTextView
        HomeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to open the SignUpActivity
                Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}