package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button login, back;
    DBHelper DB = new DBHelper(LoginActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        back = (Button) findViewById(R.id.back);


        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (user.equals("") || pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Please enter details first", Toast.LENGTH_LONG).show();
                }
                else if (!DB.checkusername(user)){
                    Toast.makeText(LoginActivity.this, "User does not exists. Try Signup", Toast.LENGTH_LONG).show();
                }
                else if (DB.checkusernamepassword(user, pass)){
                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, HomePage.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Login Unsuccessful!", Toast.LENGTH_LONG).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}