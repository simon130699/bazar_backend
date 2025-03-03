package com.todocodeacademy.bazar.controller;
import com.todocodeacademy.bazar.dto.VentaClienteProductoDTO;
import com.todocodeacademy.bazar.model.Producto;
import com.todocodeacademy.bazar.model.Venta;
import com.todocodeacademy.bazar.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/ventas") // ruta base para el controlador

public class VentaController {
    @Autowired

    private IVentaService ventServ;

    @PostMapping("/crear")
    public String crearVenta(@RequestBody Venta vent) {

        ventServ.crearVenta(vent);
        return "la venta se creo correctamente";
    }

    @GetMapping("/todasLasVentas")
    public List<Venta> traerVentas() {
        return ventServ.traerVentas();
    }

    ;

    @GetMapping("/{codigo_venta}")
    public Venta traerUnVenta(@PathVariable Long codigo_venta) {
        return ventServ.traerUnaVenta(codigo_venta);
    }

    @DeleteMapping("/eliminar/{codigo_venta}")
    public String eliminarVenta(@PathVariable Long codigo_venta) {
        ventServ.eliminarVenta(codigo_venta);
        return "se elimino la venta correctamente";
    }

    @PutMapping("/editar/{codigo_venta}")
    public Venta editarVenta(@RequestBody Venta vent) {
        ventServ.editarVenta(vent);
        return ventServ.traerUnaVenta(vent.getCodigo_venta());
    }

    @GetMapping("/productos/{codigo_venta}")
    public List<Producto> traerProductosDeVenta(@PathVariable Long codigo_venta) {
        return ventServ.traerProductosDeVenta(codigo_venta);
    }

    @GetMapping("/fecha/{fecha_venta}")
    public int sumatoriaDia(@PathVariable String fecha_venta) {
        return ventServ.sumatoriaDia(fecha_venta);
    }

    @GetMapping("/mayor_venta")
        public VentaClienteProductoDTO VentaClienteProductoDTO() {
           return ventServ.ventaMayor();
        }

}
