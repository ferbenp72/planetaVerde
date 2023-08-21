package com.example.planeta_verde.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Recolector {

    protected int cantidad, ganancia;
    protected String categoria, lugar, fecha;

    public Recolector(String categoria, int cantidad, int ganancia, String lugar, String fecha) {
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.ganancia = ganancia;
        this.lugar = lugar;
        this.fecha = fecha;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getGanancia() {
        return ganancia;
    }

    public String getLugar() {
        return lugar;
    }

    public String getFecha() {
        return fecha;
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
