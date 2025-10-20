package com.example.EcomerceUribe.repositorios;

import com.example.EcomerceUribe.modelos.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IPedidoRepositorio extends JpaRepository <Pedido, Integer> {

    //Guardar
    //Editar por ID
    //Eliminar por ID
    //Buscar por ID
    //Buscar todos los registros

    //SECCION DE CONSULTAS O QUERIES PERSONALIZADAS
    List<Pedido> findByFechaCreacion (LocalDate fechaCreacion);
    List<Pedido> findByFechaEntrega (LocalDate fechaEntrega);

}
