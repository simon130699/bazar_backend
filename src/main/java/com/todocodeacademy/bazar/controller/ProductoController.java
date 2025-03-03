package com.todocodeacademy.bazar.controller;

import com.todocodeacademy.bazar.model.Producto;
import com.todocodeacademy.bazar.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {
    @Autowired

    private IProductoService prodServ;

    @GetMapping("productos/falta_stock")
    public List<Producto> traerProductosPocoStock(){
        return prodServ.traerProductosPocoStock();
    }
    @PostMapping("/productos/crear")
    public String crearProducto(@RequestBody Producto prod){
        prodServ.crearProducto(prod);
        return "el producto fue creado con exito";
    }

    @GetMapping("/productos")
    public List<Producto> traerProductos(){
        return prodServ.traerProductos();
    };
    @GetMapping("/productos/{codigo_producto}")
    public Producto traerUnProducto(@PathVariable Long codigo_producto){
        return prodServ.traerUnProducto(codigo_producto);
    }
    @DeleteMapping("/productos/eliminar/{codigo_producto}")
    public String eliminarProducto(@PathVariable Long codigo_producto){
        prodServ.eliminarProducto(codigo_producto);
        return "se elimino el producto";
    }
    @PutMapping("/productos/editar/{codigo_producto}")
    public Producto editarProducto(@RequestBody Producto prod){
        prodServ.editarProducto(prod);
        return prodServ.traerUnProducto(prod.getCodigo_producto());
    }



}
