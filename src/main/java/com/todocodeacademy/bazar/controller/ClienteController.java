package com.todocodeacademy.bazar.controller;

import com.todocodeacademy.bazar.model.Cliente;
import com.todocodeacademy.bazar.model.Producto;
import com.todocodeacademy.bazar.service.IClienteService;
import com.todocodeacademy.bazar.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {
    @Autowired

    private IClienteService cliServ;
    @PostMapping("/clientes/crear")
    public String crearCliente(@RequestBody Cliente cli) {
        cliServ.crearCliente(cli);
        return "el cliente se creo correctamente";
    }
    @GetMapping("/clientes")
    public List<Cliente> traerClientes(){
        return cliServ.traerClientes();
    };
    @GetMapping("/clientes/{id_cliente}")
    public Cliente traerUnCliente(@PathVariable Long id_cliente){
        return cliServ.traerUnCliente(id_cliente);
    }
    @DeleteMapping("/cliente/eliminar/{id_cliente}")
    public String eliminarCliente(@PathVariable Long id_cliente){
        cliServ.eliminarCliente(id_cliente);
        return "se elimino el cliente correctamente";
    }
    @PutMapping("/cliente/editar/{id_cliente}")
    public Cliente editarCliente(@RequestBody Cliente cli){
        cliServ.editarCliente(cli);
        return cliServ.traerUnCliente(cli.getId_cliente());
    }
}
