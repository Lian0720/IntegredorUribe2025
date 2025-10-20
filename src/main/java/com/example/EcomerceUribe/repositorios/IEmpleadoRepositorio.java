package com.example.EcomerceUribe.repositorios;

import com.example.EcomerceUribe.modelos.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmpleadoRepositorio extends JpaRepository <Empleado, Integer> {

    //Guardar
    //Editar por ID
    //Eliminar por ID
    //Buscar por ID
    //Buscar todos los registros

    //SECCION DE CONSULTAS O QUERIES PERSONALIZADAS
    List<Empleado> findByCargo (String cargo);
    List<Empleado> findBySede (String sede);

}
