package com.todocodeacademy.bazar.repository;

import com.todocodeacademy.bazar.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IVentaRepository extends JpaRepository<Venta, Long> {

}
