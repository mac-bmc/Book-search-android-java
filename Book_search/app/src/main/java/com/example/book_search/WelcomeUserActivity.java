package com.example.book_search;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;


public class WelcomeUserActivity extends AppCompatActivity {

    private static final String PARAM_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_user);

        TextView userName = findViewById(R.id.home);
        String name = Objects.requireNonNull(getIntent().getExtras()).getString(PARAM_USERNAME);

        userName.setText(getString(R.string.welcome, name));

        Button logout = (Button) findViewById(R.id.logoutbtn);
        Button booksearchbutton = (Button) findViewById(R.id.booksearchbtn);
        booksearchbutton.setOnClickListener(view -> {
            Intent intent = new Intent(WelcomeUserActivity.this, BookHomeActivity.class);
            startActivity(intent);
        });

        logout.setOnClickListener(view -> {
            Toast.makeText(WelcomeUserActivity.this, getString(R.string.logout_success),
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(WelcomeUserActivity.this, LoginActivity.class);
            startActivity(intent);
        });


    }
}