package com.todocodeacademy.bazar.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigo_producto;
    private String nombre;
    private String marca;
    private Double costo;
    private Double cantidad_disponible;
    private int totalVenta;

    @ManyToOne
    @JoinColumn(name = "codigo_venta",referencedColumnName="codigo_venta")
    @JsonBackReference

    private Venta venta;

    public Producto(){

    }

    public Producto(Long codigo_producto, String nombre, String marca, Double costo, Double cantidad_disponible, int totalVenta, Venta venta) {
        this.codigo_producto = codigo_producto;
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.cantidad_disponible = cantidad_disponible;
        this.totalVenta = totalVenta;
        this.venta = venta;
    }
}
