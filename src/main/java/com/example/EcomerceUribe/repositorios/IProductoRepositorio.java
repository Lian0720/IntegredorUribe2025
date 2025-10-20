package com.example.EcomerceUribe.repositorios;

import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductoRepositorio extends JpaRepository<Producto, Integer> {

    //Guardar
    //Editar por ID
    //Eliminar por ID
    //Buscar por ID
    //Buscar todos los registros

    //SECCION DE CONSULTAS O QUERIES PERSONALIZADAS
    List<Producto> findByPrecioUnitario(Integer precioUnitario);
    List<Producto> findByMarca (String marca);

}
