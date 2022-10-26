package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText username, password, repassword;
    Button login, signup;
    String user, pass, repass;
    DBHelper DB = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        login = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = username.getText().toString();
                pass = password.getText().toString();
                repass = repassword.getText().toString();

                if (user.equals("")|| pass.equals("")|| repass.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter details first", Toast.LENGTH_LONG).show();
                }
                else if (DB.checkusername(user)){
                    Toast.makeText(MainActivity.this, "User already exists. Try Login", Toast.LENGTH_LONG).show();
                }
                else if(!pass.equals(repass)){
                    Toast.makeText(MainActivity.this, "Password and Re-password do not match", Toast.LENGTH_LONG).show();
                }
                else if(DB.insertData(user, pass)){
                    Toast.makeText(MainActivity.this, "Registration Successful!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, HomePage.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Registration Failed!", Toast.LENGTH_LONG).show();
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }

}
