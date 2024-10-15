package com.geeks.homework;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {


    private EditText emailInput, passwordInput;
    private Button loginButton;
    private TextView welcomeText, forgotPassword;
    private String correctPassword = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        loginButton = findViewById(R.id.login_button);
        welcomeText = findViewById(R.id.welcome_text);
        forgotPassword = findViewById(R.id.forgot_password);

        emailInput.addTextChangedListener(inputWatcher);
        passwordInput.addTextChangedListener(inputWatcher);

        loginButton.setOnClickListener(v -> {
            String enteredPassword = passwordInput.getText().toString();
            if (enteredPassword.equals(correctPassword)) {
                emailInput.setVisibility(View.GONE);
                passwordInput.setVisibility(View.GONE);
                loginButton.setVisibility(View.GONE);
                forgotPassword.setVisibility(View.GONE);

                Snackbar.make(v, "Вы успешно зарегистрировались", Snackbar.LENGTH_LONG).show();
            } else {
                Snackbar.make(v, "Неправильный пароль", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private final TextWatcher inputWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (!email.isEmpty() && !password.isEmpty()) {
                loginButton.setEnabled(true);
                loginButton.setBackgroundTintList(getResources().getColorStateList(R.color.orange));
            } else {
                loginButton.setEnabled(false);
                loginButton.setBackgroundTintList(getResources().getColorStateList(android.R.color.darker_gray));
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
}