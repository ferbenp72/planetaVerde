package com.example.planeta_verde;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planeta_verde.models.Users;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class RegisterActivity extends AppCompatActivity {

    TextView login, usernameText, lastNameText, emailText, userText, passwordRegisterText, confirmPasswordText;
    View usernameTextBox, lastNameTextBox, emailTextBox, userTextBox, passwordRegisterTextBox, confirmPasswordTextBox;
    Button registrarBoton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        login = findViewById(R.id.loginTextback);
        usernameText = findViewById(R.id.usernameText);
        lastNameText = findViewById(R.id.lastNameText);
        emailText = findViewById(R.id.emailText);
        userText = findViewById(R.id.userText);
        passwordRegisterText = findViewById(R.id.passwordRegisterText);
        confirmPasswordText = findViewById(R.id.confirmPasswordText);
        registrarBoton = findViewById(R.id.registrarBoton);

        usernameTextBox = findViewById(R.id.usernameTextBox);
        lastNameTextBox = findViewById(R.id.lastNameTextBox);
        emailTextBox = findViewById(R.id.emailTextBox);
        userTextBox = findViewById(R.id.userTextBox);
        passwordRegisterTextBox = findViewById(R.id.passwordRegisterTextBox);
        confirmPasswordTextBox = findViewById(R.id.confirmPasswordTextBox);



        Intent loginPage = new Intent(this, LoginActivity.class);
        Intent homePage = new Intent(this, HomeActivity.class);

        registrarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (validateUser()){
                        Users users = new Users(usernameText.getText().toString(), lastNameText.getText().toString(),
                                emailText.getText().toString(), userText.getText().toString(),
                                passwordRegisterText.getText().toString());
                        createUsers(users);
                        Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        Users.setActiveUser(userText.getText().toString());

                        sleep(300);
                        startActivity(homePage);
                        finish();

                    } else{
                        Toast.makeText(RegisterActivity.this, "Validar campos", Toast.LENGTH_SHORT).show();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(loginPage);
                finish();
            }
        });
    }

    //Validacion de campos vacios y cambio de color
    public boolean validateUser(){
        Drawable textBoxError = ContextCompat.getDrawable(RegisterActivity.this, R.drawable.textboxerror);
        boolean estado = true;
        if (usernameText.getText().toString().isEmpty()){
            usernameTextBox.setBackground(textBoxError);
            //Toast.makeText(RegisterActivity.this, "Nombre es requerido",Toast.LENGTH_SHORT).show();
            estado = false;
        }
        if (lastNameText.getText().toString().isEmpty()){
            lastNameTextBox.setBackground(textBoxError);
            //Toast.makeText(RegisterActivity.this, "Apellidos es requerido",Toast.LENGTH_SHORT).show();
            estado = false;
        }
        if (emailText.getText().toString().isEmpty()){
            emailTextBox.setBackground(textBoxError);
            //Toast.makeText(RegisterActivity.this, "Correo electronico es requerido",Toast.LENGTH_SHORT).show();
            estado = false;
        }
        if (userText.getText().toString().isEmpty()){
            userTextBox.setBackground(textBoxError);
            //Toast.makeText(RegisterActivity.this, "usuario es requerido",Toast.LENGTH_SHORT).show();
            estado = false;
        }
        if (passwordRegisterText.getText().toString().isEmpty()){
            passwordRegisterTextBox.setBackground(textBoxError);
            //Toast.makeText(RegisterActivity.this, "contreseña es requerida",Toast.LENGTH_SHORT).show();
            estado = false;
        }
        if (!passwordRegisterText.getText().toString().equals(confirmPasswordText.getText().toString())){
            passwordRegisterTextBox.setBackground(textBoxError);
            confirmPasswordText.setBackground(textBoxError);
            Toast.makeText(RegisterActivity.this, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            estado = false;
        }
        return estado;
    }

    public void createUsers(Users user){
        File file = new File(getFilesDir(), "users.txt");
        try {
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(user.getFirstname()+","+
                    user.getSurname()+","+user.getEmail()+
                    ","+user.getUsername()+","+user.getPassword());
            bufferedWriter.newLine();
            bufferedWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}