package com.example.planeta_verde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    TextView register;
    Button ingresar;
    TextView username;
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent registerPage = new Intent(this, RegisterActivity.class);
        Intent homePage = new Intent(this, HomeActivity.class);

        register = findViewById(R.id.register);
        ingresar = findViewById(R.id.ingresarBoton);
        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);


        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
                    Log.i("Usuario",username.getText().toString());
                    Log.i("pass",password.getText().toString());

                    startActivity(homePage);
                    finish();

                }else {
                    Toast.makeText(LoginActivity.this, "Debes ingresar todos los valores", Toast.LENGTH_SHORT).show();
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(registerPage);
                finish();
            }
        });



    }
}