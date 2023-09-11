package com.example.loginui;

import androidx.appcompat.app.AppCompatActivity;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

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