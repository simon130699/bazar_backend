package com.todocodeacademy.bazar.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter @Setter
public class VentaClienteProductoDTO implements Serializable {
    private Long codigo_venta;
    private Double total;
    private int cantProductos;
    private String nombreCliente;
    private String apellidoCliente;

    public VentaClienteProductoDTO() {

    }

    public VentaClienteProductoDTO(Long codigo_venta, Double total, int cantProductos, String nombreCliente, String apellidoCliente) {
        this.codigo_venta = codigo_venta;
        this.total = total;
        this.cantProductos = cantProductos;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
    }
}
