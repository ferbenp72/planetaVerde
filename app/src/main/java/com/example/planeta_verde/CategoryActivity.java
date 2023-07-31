package com.example.planeta_verde;

import androidx.appcompat.app.AppCompatActivity;
import com.example.planeta_verde.RecyclerActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class CategoryActivity extends AppCompatActivity {
    ImageView icon_home;
    ImageView icon_recicler;
    ImageView icon_info;
    ImageView icon_user;

    View plastico;
    View carton;
    View vidrio;
    View metal;
    View tecno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Intent categoryPage = new Intent(this, CategoryActivity.class);
        Intent homePage = new Intent(this, HomeActivity.class);
        Intent LoginPage = new Intent(this, LoginActivity.class);
        Intent recycler = new Intent(this, RecyclerActivity.class);
        icon_home = findViewById(R.id.icon_home);
        icon_recicler = findViewById(R.id.icon_recicler);
        icon_info = findViewById(R.id.icon_info);
        icon_user = findViewById(R.id.icon_user);

        plastico = findViewById(R.id.plastico);
        carton = findViewById(R.id.carton);
        vidrio = findViewById(R.id.vidrio);
        metal = findViewById(R.id.metal);
        tecno = findViewById(R.id.tecno);


        plastico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recycler.putExtra("categoria","Plastico");
                startActivity(recycler);
                finish();
            }
        });

        carton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recycler.putExtra("categoria","carton");
                startActivity(recycler);
                finish();
            }
        });

        vidrio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recycler.putExtra("categoria","vidrio");
                startActivity(recycler);
                finish();
            }
        });
        metal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recycler.putExtra("categoria","metal");
                startActivity(recycler);
                finish();
            }
        });
        tecno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recycler.putExtra("categoria","tecno");
                startActivity(recycler);
                finish();
            }
        });






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
                Toast.makeText(getApplicationContext(),"En construcción", Toast.LENGTH_LONG).show();

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
}