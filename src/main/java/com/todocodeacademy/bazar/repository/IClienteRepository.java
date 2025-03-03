package com.todocodeacademy.bazar.repository;

import com.todocodeacademy.bazar.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {
}
