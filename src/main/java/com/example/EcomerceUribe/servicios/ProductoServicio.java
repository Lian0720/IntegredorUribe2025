package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO;
import com.example.EcomerceUribe.modelos.DTOS.UsuarioGenericoDTO;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.modelos.Usuario;
import com.example.EcomerceUribe.modelos.mapas.IProductoMapa;
import com.example.EcomerceUribe.repositorios.IProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicio {

    @Autowired
    private IProductoRepositorio repositorio;

    @Autowired
    private IProductoMapa mapa;

    public ProductoDTO guardarProducto(Producto datosProducto) {

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

    //Buscar productos todos
    public List<ProductoDTO> buscarTodosLosProductos() {
        List<Producto> listadeProductosConsultados = this.repositorio.findAll();
        return this.mapa.convertir_list_a_listdto(listadeProductosConsultados);
    }
    //Buscar productos por id
    public ProductoDTO buscarProductoPorID(Integer id) {
        Optional<Producto> ProductoQueEstoyBuscando = this.repositorio.findById(id);
        if (!ProductoQueEstoyBuscando.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningún producto con el id " + id + "suministrado"
            );
        }
        Producto ProductoEncontrado = ProductoQueEstoyBuscando.get();
        return this.mapa.convertir_producto_a_productodto(ProductoEncontrado);
    }

    //Eliminar producto
    public void eliminarProducto(Integer id) {
        Optional<Producto> ProductoQueEstoyBuscando = this.repositorio.findById(id);
        if (!ProductoQueEstoyBuscando.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun producto con el id " + id + "suministrado"
            );
        }
        Producto ProductoEncontrado = ProductoQueEstoyBuscando.get();
        try {
            this.repositorio.delete(ProductoEncontrado);
        } catch (Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se puedo eliminar el producto, " + error.getMessage()
            );
        }
    }

    //Modificar datos del producto
    public ProductoDTO actualizarProducto(Integer id, Producto datosActualizados) {
        Optional<Producto> ProductoQueEstoyEditanto = this.repositorio.findById(id);
        if (!ProductoQueEstoyEditanto.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun producto con el id " + id + "suministrado"
            );
        }
        Producto ProductoEncontrado = ProductoQueEstoyEditanto.get();

        //Aplique validaciones sobre datos enviados desde el Frond

        //Actualizo los campos que permitieron modificar

        //Nombre //Correo

        ProductoEncontrado.setNombres(datosActualizados.getNombres());
        ProductoEncontrado.setCategoria(datosActualizados.getCategoria());

        //Concluyo actualizacion en la base de datos
        Producto ProductoActualizado = this.repositorio.save(ProductoEncontrado);

        if (ProductoActualizado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al actualizar el producto en la base de datos. Intentar nuevamente"
            );
        }

        return this.mapa.convertir_producto_a_productodto(ProductoActualizado);

    }

}




