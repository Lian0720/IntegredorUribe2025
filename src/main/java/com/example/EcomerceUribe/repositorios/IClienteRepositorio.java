package com.example.EcomerceUribe.repositorios;

import com.example.EcomerceUribe.modelos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClienteRepositorio extends JpaRepository <Cliente, Integer> {

    //Guardar
    //Editar por ID
    //Eliminar por ID
    //Buscar por ID
    //Buscar todos los registros

    //SECCION DE CONSULTAS O QUERIES PERSONALIZADAS
    List<Cliente> findByCalificacion (Double Calificacion);
    List<Cliente> findByCiudad (String ciudad);

}
