package com.example.planeta_verde;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planeta_verde.models.Users;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {


    TextView register;
    Button ingresar;
    TextView username;
    TextView password;

    String userActive;

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

        File fileReader = new File(getFilesDir(), "users.txt");
        try {
            FileWriter writer = new FileWriter(fileReader, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Users> listaCompletaUsuarios = validateUser(fileReader);



        //Fucniones de click en login
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validacion de datos de usuario
                if(!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){


                    try {
                        if(validateCredencial(listaCompletaUsuarios)){
                            Toast.makeText(LoginActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                            sleep(300);
                            startActivity(homePage);
                        }else {
                            Toast.makeText(LoginActivity.this, "Usuario o contraseña invalido",Toast.LENGTH_SHORT).show();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }


                    //Log.i("Usuario de archivo",usuario.getUsername());
                    Log.i("Usuario",username.getText().toString());
                    Log.i("pass",password.getText().toString());



                }else {
                    Toast.makeText(LoginActivity.this, "Debes ingresar todos los valores", Toast.LENGTH_SHORT).show();
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(registerPage);
            }
        });



    }

    //Crear lista de usuarios en diccionario
    public static List<Users> validateUser(File file){
        List<Users> listaUsuarios = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String lineUsers;
            while((lineUsers = reader.readLine()) != null){
                String[] usuarios = lineUsers.split(",");
                String nombre = usuarios[0];
                String apellidos = usuarios[1];
                String correo = usuarios[2];
                String usuario = usuarios[3];
                String password = usuarios[4];

                Users usuariosObj = new Users(nombre,apellidos,correo,usuario,password);
                listaUsuarios.add(usuariosObj);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }
     public boolean validateCredencial(List<Users> listaCompletaUsuarios){
         for (Users usuario : listaCompletaUsuarios){
             if(usuario.getUsername().equals(username.getText().toString()) || usuario.getEmail().equals(username.getText().toString())){
                 if(usuario.getPassword().equals(password.getText().toString())){
                     Users.setActiveUser(usuario.getUsername());
                     Log.i("usuario", usuario.toString());
                     return true;
                 }
             }
         }
        return false;
     }

}