package com.example.planeta_verde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planeta_verde.models.Recolector;
import com.example.planeta_verde.models.Users;

import java.io.File;
import java.io.FileReader;
import java.util.List;

public class EstadisticasActivity extends AppCompatActivity {

    TextView barraPlastico, barraCarton, barraVidrio, barraMetal, barraTecno;
    TextView plasticoTotal,cartonTotal,vidrioTotal,metalTotal,tecnoTotal;
    ImageView icon_home;
    ImageView icon_recicler;
    ImageView icon_info;
    ImageView icon_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        icon_home = findViewById(R.id.icon_home);
        icon_recicler = findViewById(R.id.icon_recicler);
        icon_info = findViewById(R.id.icon_info);
        icon_user = findViewById(R.id.icon_user);

        Intent categoryPage = new Intent(this, CategoryActivity.class);
        Intent homePage = new Intent(this, HomeActivity.class);
        Intent LoginPage = new Intent(this, LoginActivity.class);


        float percent= 30;
        File recolectorFile = new File(getFilesDir(), Users.getActiveUser()+"-recolectorData.txt");

        List<Recolector> listaCompleta = Recolector.readFile(recolectorFile);

        if(!listaCompleta.isEmpty()){
            Log.i("lista",listaCompleta.get(0).getCategoria());
        }else {
            Log.i("Lista","La lista está vacia");
        }
        float gananciasTotales = 0;
        float pesoTotal = 0;
        float gananciasPlastico = 0;
        float pesoPlastico= 0;
        float gananciasCarton = 0;
        float pesoCarton= 0;
        float gananciasVidrio = 0;
        float pesoVidrio= 0;
        float gananciasMetal = 0;
        float pesoMetal= 0;
        float gananciasTecno = 0;
        float pesoTecno= 0;

        barraPlastico = findViewById(R.id.barraPlastico);
        barraCarton = findViewById(R.id.barraCarton);
        barraVidrio = findViewById(R.id.barraVidrio);
        barraMetal = findViewById(R.id.barraMetal);
        barraTecno = findViewById(R.id.barraTecno);

        plasticoTotal = findViewById(R.id.plasticoTotal);
        cartonTotal = findViewById(R.id.cartonTotal);
        vidrioTotal = findViewById(R.id.vidrioTotal);
        metalTotal = findViewById(R.id.metalTotal);
        tecnoTotal = findViewById(R.id.tecnoTotal);




        //Consuta total de estadisticas
        for(Recolector recolector : listaCompleta){
            gananciasTotales += recolector.getGanancia();
            pesoTotal += recolector.getCantidad();
        }

        //Consulta de plasticos
        for(Recolector recolector : listaCompleta){
            if(recolector.getCategoria().equals("Plástico")){
                gananciasPlastico += recolector.getGanancia();
                pesoPlastico += recolector.getCantidad();
            }
            if(recolector.getCategoria().equals("Cartón")){
                gananciasCarton += recolector.getGanancia();
                pesoCarton += recolector.getCantidad();
            }
            if(recolector.getCategoria().equals("Vidrio")){
                gananciasVidrio += recolector.getGanancia();
                pesoVidrio += recolector.getCantidad();
            }
            if(recolector.getCategoria().equals("Metal")){
                gananciasMetal += recolector.getGanancia();
                pesoMetal += recolector.getCantidad();
            }
            if(recolector.getCategoria().equals("Tecnología")){
                gananciasTecno += recolector.getGanancia();
                pesoTecno += recolector.getCantidad();
            }


        }

        float porcentajePlastico=gananciasPlastico/gananciasTotales*100;
        float porcentajeCarton=gananciasCarton/gananciasTotales*100;
        float porcentajeVidrio =gananciasVidrio/gananciasTotales*100;
        float porcentajeMetal=gananciasMetal/gananciasTotales*100;
        float porcentajeTecno=gananciasTecno/gananciasTotales*100;

        plasticoTotal.setText(String.valueOf(gananciasPlastico));
        cartonTotal.setText(String.valueOf(gananciasCarton));
        vidrioTotal.setText(String.valueOf(gananciasVidrio));
        metalTotal.setText(String.valueOf(gananciasMetal));
        tecnoTotal.setText(String.valueOf(gananciasTecno));


        barraPlastico.setText(barras(porcentajePlastico));
        barraCarton.setText(barras(porcentajeCarton));
        barraVidrio.setText(barras(porcentajeVidrio));
        barraMetal.setText(barras(porcentajeMetal));
        barraTecno.setText(barras(porcentajeTecno));

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
    }

    public String barras(float valorPorcentaje){



        int textInInch = barraPlastico.getMinWidth();
        Paint paint = new Paint();

        paint.setTextSize(barraPlastico.getTextSize());
        float anchoLetra = paint.measureText("█");
        float letras = textInInch/anchoLetra;
        int valor = Math.round(letras * (valorPorcentaje/100));

        StringBuilder barras = new StringBuilder();
        for (int i = 0; i<valor; i++){
            barras.append("█");
        }
        return barras.toString();
    }
}