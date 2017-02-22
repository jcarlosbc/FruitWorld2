package com.fruitw2.hispano_mx.fruitworld2.Model;

/**
 * Created by jcblas on 2/14/2017.
 */

public class Fruta {

    public String nombre;
    public String descripcion;
    public int contador;
    public int imagen;
    public int icono;

    public Fruta(String nombre, String descripcion, int contador, int imagen, int icono) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.contador =  contador;
        this.imagen = imagen;
        this.icono = icono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }

    public void resetContador(){
        this.contador=0;
    }

    public int addCantidad(){
        if (contador<10)
            this.contador++;
        return  this.contador;
    }
}
