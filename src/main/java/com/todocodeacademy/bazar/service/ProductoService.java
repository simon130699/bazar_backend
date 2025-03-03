package com.todocodeacademy.bazar.service;

import com.todocodeacademy.bazar.model.Producto;
import com.todocodeacademy.bazar.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private IProductoRepository repoProduct;
    @Override
    public void crearProducto(Producto prod) {
        repoProduct.save(prod);
    }

    @Override
    public List<Producto> traerProductos() {
        List<Producto> lisProductos= repoProduct.findAll();
        return lisProductos;
    }

    @Override
    public Producto traerUnProducto(Long codigo_producto) {
       Producto prod=repoProduct.findById(codigo_producto).orElse(null);
       return prod;
    }

    @Override
    public void eliminarProducto(Long codigo_producto) {
        repoProduct.deleteById(codigo_producto);
    }

    @Override
    public void editarProducto(Producto productoEditado) {
        this.crearProducto(productoEditado);
    }


//4. Obtener todos los productos cuya cantidad_disponible sea menor a 5
//a. Métodos HTTP: GET
//b. Endpoint:
//localhost:8080/productos/falta_stock
    @Override
    public List<Producto> traerProductosPocoStock() {
        ArrayList<Producto> listProdPocoStock = new ArrayList<Producto>();
        for(Producto prod : traerProductos()){
            if(prod.getCantidad_disponible()<5){
                listProdPocoStock.add(prod);
            }
        }
        return listProdPocoStock;
    }
}
