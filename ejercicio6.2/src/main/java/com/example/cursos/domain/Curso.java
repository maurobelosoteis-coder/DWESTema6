package com.example.cursos.domain;

public class Curso {
    private Long id;
    private String nombre;
    private Double precio;
    private Tematica tematica;

    public Curso() {
    }

    public Curso(Long id, String nombre, Double precio, Tematica tematica) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.tematica = tematica;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public Tematica getTematica() { return tematica; }
    public void setTematica(Tematica tematica) { this.tematica = tematica; }
}
