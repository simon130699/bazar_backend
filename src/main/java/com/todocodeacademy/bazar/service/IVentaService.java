package com.todocodeacademy.bazar.service;

import com.todocodeacademy.bazar.dto.VentaClienteProductoDTO;
import com.todocodeacademy.bazar.model.Producto;
import com.todocodeacademy.bazar.model.Venta;

import java.time.LocalDate;
import java.util.List;

public interface IVentaService {
    //alta
    public void crearVenta(Venta vent);

    //lectura
    public List<Venta> traerVentas();

    //lectura 1 sola venta
    public Venta traerUnaVenta(Long codigo_venta);

    //eliminar
    public void eliminarVenta(Long codigo_venta);

    //editar
    public void editarVenta(Venta vent);


    public List<Producto> traerProductosDeVenta(Long codigo_venta);
    public int sumatoriaDia(String fecha_venta);

    VentaClienteProductoDTO ventaMayor();
}
