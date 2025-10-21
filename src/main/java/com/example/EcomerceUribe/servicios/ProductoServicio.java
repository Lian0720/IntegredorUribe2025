package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.modelos.Usuario;
import com.example.EcomerceUribe.modelos.mapas.IProductoMapa;
import com.example.EcomerceUribe.repositorios.IProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductoServicio {

    @Autowired
    private IProductoRepositorio repositorio;

    @Autowired
    private IProductoMapa mapa;

    public ProductoDTO guardarProducto (Producto datosProducto) {

        //validaciones 3

        //validacion nombre no vacío
        if (datosProducto.getNombres() == null || datosProducto.getNombres().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El nombre del producto es obligatorio"
            );
        }
        //validación categoría no vacío
        if (datosProducto.getCategoria() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La categoria del producto es obligatorio"
            );
        }
        //Validación precioUnitario no vacío
        if (datosProducto.getPrecioUnitario() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El precio del producto es obligatorio"
            );
        }

        //Intentar guardar el producto
        Producto productoQueGuardoElRepo = this.repositorio.save(datosProducto);
        if (productoQueGuardoElRepo == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el producto en la base de datos"
            );
        }
        //Retornar el dto al controlador
        return this.mapa.convertir_producto_a_productodto(productoQueGuardoElRepo);
    }

}



