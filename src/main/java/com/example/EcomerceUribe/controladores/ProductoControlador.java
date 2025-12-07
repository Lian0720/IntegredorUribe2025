package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO;
import com.example.EcomerceUribe.modelos.DTOS.UsuarioGenericoDTO;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.servicios.ProductoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Controlador para operaciones en la tabla productos")

public class ProductoControlador {
    //llamar al servicio
    @Autowired
    ProductoServicio servicio;

    //listar los posibles llamados a los servicios disponibles

    //se crean funciones por cada posible llamado y se les agrega el metodo HTTP correspondiente (GET,PUT,POST, DELETE)

    //GUARDAR
    @Operation(summary = "Crear un producto en la BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<ProductoDTO> guardar(@RequestBody Producto datos){
        ProductoDTO respuesta=this.servicio.guardarProducto(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    //LISTAR
    @Operation(summary = "Listar los productos de la BD")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ProductoDTO>>listar(){
        List<ProductoDTO> respuesta=this.servicio.buscarTodosLosProductos();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //buscarPorId
    @Operation(summary = "Buscar un producto en la BD")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ProductoDTO>buscarPorId(@PathVariable Integer id){
        ProductoDTO respuestta=this.servicio.buscarProductoPorID(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuestta);
    }

    //eliminar
    @Operation(summary = "Eliminar un producto en la BD")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void>eliminar(@PathVariable Integer id){
        this.servicio.buscarProductoPorID(id);
        return ResponseEntity.noContent().build();
    }

    //modificar
    @Operation(summary = "Modificar un producto en la BD")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ProductoDTO>modificar(@PathVariable Integer id, @RequestBody Producto datos){
        ProductoDTO respuesta = this.servicio.actualizarProducto(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

}
