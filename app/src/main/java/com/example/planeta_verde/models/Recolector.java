package com.example.planeta_verde.models;

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
}
