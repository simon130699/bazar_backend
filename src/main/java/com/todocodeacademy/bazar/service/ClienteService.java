package com.todocodeacademy.bazar.service;

import ch.qos.logback.core.net.server.Client;
import com.todocodeacademy.bazar.model.Cliente;
import com.todocodeacademy.bazar.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService{
    @Autowired
    private IClienteRepository repoCli;

    @Override
    public void crearCliente(Cliente cli) {
        repoCli.save(cli);
    }

    @Override
    public List<Cliente> traerClientes() {
        List<Cliente> listaCli = repoCli.findAll();
        return listaCli;
    }

    @Override
    public Cliente traerUnCliente(Long id_cliente) {
        Cliente cli = repoCli.findById(id_cliente).orElse(null);
        return cli;
    }

    @Override
    public void eliminarCliente(Long id_cliente) {
        repoCli.deleteById(id_cliente);

    }

    @Override
    public void editarCliente(Cliente cliEdit) {
        this.crearCliente(cliEdit);
    }
}
