package model;

public class Producto {

    private int id;                 // lo rellena la BD (AUTO_INCREMENT)
    private String nombre;
    private String descripcion;
    private int cantidad;
    private double precio;

    public Producto(String nombre, String descripcion, int cantidad, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }
}
