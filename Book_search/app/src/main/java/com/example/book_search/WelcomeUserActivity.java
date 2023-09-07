package com.example.book_search;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class WelcomeUserActivity extends AppCompatActivity {

    TextView uname;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_user);

        uname=findViewById(R.id.home);
        name=getIntent().getExtras().getString("username");

        uname.setText("Welcome "+name);

        Button logoutbtn = (Button) findViewById(R.id.logoutbtn);
        Button booksearchbtn=(Button) findViewById(R.id.booksearchbtn) ;
        booksearchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(WelcomeUserActivity.this,BookHomeActivity.class);
                startActivity(intent);
            }
        });

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WelcomeUserActivity.this,"Logout succesful",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(WelcomeUserActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });




    }
}