package com.todocodeacademy.bazar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter

public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
private Long codigo_venta;
private LocalDate fecha_venta;
private Double total;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName="id_cliente") //

    private Cliente unCliente;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.MERGE)
    @JsonManagedReference
    private List<Producto> listaProductos;
    public Venta() {
    }

    public Venta(Long codigo_venta, LocalDate fecha_venta, Double total, List<Producto> listaProductos, Cliente unCliente) {
        this.codigo_venta = codigo_venta;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.listaProductos = listaProductos;
        this.unCliente = unCliente;
    }
}
