package com.example.loginui;

import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button loginButton;
    EditText passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        passwordEditText = findViewById(R.id.password);


        passwordEditText.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2; // Index for the right drawable
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (passwordEditText.getRight() - passwordEditText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // Toggle password visibility
                        if (passwordEditText.getInputType() == 129) {
                            passwordEditText.setInputType(1);
                            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_lock_24, 0, R.drawable.ic_eye_closed, 0); // Switch to "read" icon
                        } else {
                            passwordEditText.setInputType(129);
                            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_lock_24, 0, R.drawable.ic_eye_open , 0); // Switch to "unread" icon
                        }
                        // Move the cursor to the end of the text
                        passwordEditText.setSelection(passwordEditText.getText().length());
                        return true;
                    }
                }
                return false;
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();

                // You can check the credentials here
                if (userEmail.equals("user") && userPassword.equals("1234")) {
                    // Username and password match
                    // Perform the API request here if needed

                    // Make an API request
                    OkHttpClient client = new OkHttpClient();
                    FormBody formBody = new FormBody.Builder()
                            .add("email", userEmail)
                            .add("password", userPassword)
                            .build();

                    Request request = new Request.Builder()
                            .url("https://ssforcenew.ssgbd.com/api/v2/login")
                            .post(formBody)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                // Handle a successful response here
                                String responseData = response.body().string();
                                // Parse and process responseData as needed
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                // Handle an unsuccessful response here (e.g., show an error message)
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call call, IOException e) {
                            // Handle network failure here (e.g., show a network error message)
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "Network Error!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                } else {
                    // Username and password do not match
                    Toast.makeText(MainActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}