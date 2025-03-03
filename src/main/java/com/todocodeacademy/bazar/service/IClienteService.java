package com.todocodeacademy.bazar.service;

import com.todocodeacademy.bazar.model.Cliente;
import com.todocodeacademy.bazar.model.Producto;

import java.util.List;

public interface IClienteService {
    //alta
    public void crearCliente(Cliente cli);

    //lectura
    public List<Cliente> traerClientes();

    //lectura 1 solo cliente
    public Cliente traerUnCliente(Long id_cliente);

    //eliminar
    public void eliminarCliente(Long id_cliente);

    //editar
    public void editarCliente(Cliente cli);

}
