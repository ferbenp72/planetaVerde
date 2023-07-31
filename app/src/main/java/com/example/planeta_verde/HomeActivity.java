package com.example.planeta_verde;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planeta_verde.models.Recolector;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    TextView fechaActual;
    View categorias;
    ImageView icon_home;
    ImageView icon_recicler;
    ImageView icon_info;
    ImageView icon_user;

    TextView total_en_gramos;
    TextView total_en_cantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fechaActual = findViewById(R.id.fechaAhora);
        fechaActual.setText(dateNow());
        categorias = findViewById(R.id.categorias);

        Intent LoginPage = new Intent(this, LoginActivity.class);
        Intent categoryPage = new Intent(this, CategoryActivity.class);
        Intent homePage = new Intent(this, HomeActivity.class);
        icon_home = findViewById(R.id.icon_home);
        icon_recicler = findViewById(R.id.icon_recicler);
        icon_info = findViewById(R.id.icon_info);
        icon_user = findViewById(R.id.icon_user);
        total_en_gramos = findViewById(R.id.TotalEnGramos);
        total_en_cantidad = findViewById(R.id.ganacias);

        //Traer los datos del archivo txt
        File recolectorFile = new File(getFilesDir(),"recolectorData.txt");

        List<Recolector> listaCompleta = readFile(recolectorFile);

        if(!listaCompleta.isEmpty()){
            Log.i("lista",listaCompleta.get(0).getCategoria());
        }else {
            Log.i("Lista","La lista está vacia");
        }


        int totalCantidad = 0;
        int totalGanancia = 0;

        for (Recolector recolector : listaCompleta){
            totalCantidad += recolector.getCantidad();
            totalGanancia += recolector.getGanancia();
        }

        total_en_gramos.setText(totalCantidad+"g");
        total_en_cantidad.setText("$"+totalGanancia);





        //Funciones del menú inferior
        icon_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(homePage);
                finish();

            }
        });
        icon_recicler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(categoryPage);
                finish();

            }
        });
        icon_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"En construcción", Toast.LENGTH_SHORT).show();

            }
        });
        icon_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Cerrar sesión", Toast.LENGTH_LONG).show();
                startActivity(LoginPage);
                finish();

            }
        });



        categorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(categoryPage);
                finish();

            }
        });

    }

    private String dateNow(){
        long ahora = System.currentTimeMillis();
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return df.format(ahora);
    }

    public static List<Recolector> readFile(File file){
        List<Recolector> listaRecolectora = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line= br.readLine()) != null){
                String[] data = line.split(",");
                String categoria = data[0];
                int cantidad = Integer.parseInt(data[1]);
                int ganancia = Integer.parseInt(data[2]);
                String lugar = data[3];
                String fecha = data[4];
                Recolector objRecolector = new Recolector(categoria,cantidad,ganancia,lugar,fecha);
                listaRecolectora.add(objRecolector);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaRecolectora;
    }
}