package com.example.planeta_verde;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planeta_verde.models.Recolector;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class RecyclerActivity extends AppCompatActivity {
    //Declaracion de variables
    ImageView icon_home;
    ImageView icon_recicler;
    ImageView icon_info;
    ImageView icon_user;
    TextView setText;
    ImageView setIcon;
    Button ingresar;
    Button cancelar;
    EditText cantidad;
    EditText ganancia;
    EditText lugar;
    EditText fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        Intent categoryPage = new Intent(this, CategoryActivity.class);
        Intent homePage = new Intent(this, HomeActivity.class);
        Intent LoginPage = new Intent(this, LoginActivity.class);

        //Inicializar variables
        icon_home = findViewById(R.id.icon_home);
        icon_recicler = findViewById(R.id.icon_recicler);
        icon_info = findViewById(R.id.icon_info);
        icon_user = findViewById(R.id.icon_user);
        ingresar = findViewById(R.id.recolectar);
        cancelar = findViewById(R.id.cancelar);
        cantidad = findViewById(R.id.cantidad);
        ganancia = findViewById(R.id.ganancias);
        lugar = findViewById(R.id.lugar_de_cambio);
        fecha = findViewById(R.id.fecha);

        setText = findViewById(R.id.set_categoria);



        String categorias = getIntent().getStringExtra("categoria");
        setCategories(categorias);

        //Ingresar valores
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cantidad.getText().toString().isEmpty() &&
                !ganancia.getText().toString().isEmpty() &&
                !lugar.getText().toString().isEmpty() &&
                !fecha.getText().toString().isEmpty()){
                    Recolector recolector = new Recolector(
                            setText.getText().toString(),
                            Integer.parseInt(cantidad.getText().toString()),
                            Integer.parseInt(ganancia.getText().toString()),
                            lugar.getText().toString(), fecha.getText().toString());
                    saverRecolecter(recolector);
                    Toast.makeText(RecyclerActivity.this, "Producto registrado", Toast.LENGTH_LONG).show();
                    startActivity(categoryPage);
                }
            }
        });

        //Cancelar registro de producto
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(categoryPage);
            }
        });



        //Metodos de menú inferior
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
                Toast.makeText(getApplicationContext(), "En construcción", Toast.LENGTH_LONG).show();

            }
        });
        icon_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Cerrar sesión", Toast.LENGTH_LONG).show();
                startActivity(LoginPage);
                finish();

            }
        });


    }

    public void saverRecolecter(Recolector recolector){
        File file = new File(getFilesDir(),"recolectorData.txt");
        try {
            FileWriter writer = new FileWriter(file,true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(recolector.getCategoria()+","+recolector.getCantidad()+","+recolector.getGanancia()+","+
                    recolector.getLugar()+","+recolector.getFecha());
            bufferedWriter.newLine();
            bufferedWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }



    }


    public void setCategories(String categorias){

        setIcon = findViewById(R.id.set_icon);

        switch (categorias){
            case "pastico":
                setText.setText(getString(R.string.plastico));
                setIcon.setImageResource(R.drawable.plastic);
                break;
            case "carton":
                setText.setText(getString(R.string.carton));
                setIcon.setImageResource(R.drawable.milk);
                break;
            case "vidrio":
                setText.setText(getString(R.string.vidrio));
                setIcon.setImageResource(R.drawable.glass);
                break;
            case "metal":
                setText.setText(getString(R.string.metal));
                setIcon.setImageResource(R.drawable.nut);
                break;
            case "tecno":
                setText.setText(getString(R.string.tecno));
                setIcon.setImageResource(R.drawable.monitor);
                break;
        }


    }
}