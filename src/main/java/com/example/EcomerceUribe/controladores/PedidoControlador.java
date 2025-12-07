package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO;
import com.example.EcomerceUribe.modelos.DTOS.UsuarioGenericoDTO;
import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.modelos.Usuario;
import com.example.EcomerceUribe.servicios.PedidoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Controlador para operaciones en la tabla pedidos")

public class PedidoControlador {
    //llamar al servicio
    @Autowired
    PedidoServicio servicio;

    //listar los posibles llamados a los servicios disponibles

    //se crean funciones por cada posible llamado y se les agrega el metodo HTTP correspondiente (GET,PUT,POST, DELETE)

    //GUARDAR
    @Operation(summary = "Crear un pedido en la BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<PedidoDTO> guardar(@RequestBody Pedido datos){
        PedidoDTO respuesta=this.servicio.guardarPedido(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    //LISTAR
    @Operation(summary = "Listar todos los pedidos en la BD")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<PedidoDTO>>listar(){
        List<PedidoDTO> respuesta=this.servicio.buscarTodosLosPedidos();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //burcarPorId
    @Operation(summary = "Buscar un pedido en la BD")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PedidoDTO>buscarPorId(@PathVariable Integer id){
        PedidoDTO respuestta=this.servicio.buscarPedidoPorID(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuestta);
    }

    //eliminar
    @Operation(summary = "Eliminar un pedido en la BD")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void>eliminar(@PathVariable Integer id){
        this.servicio.buscarPedidoPorID(id);
        return ResponseEntity.noContent().build();
    }

    //modificar
    @Operation(summary = "Modificar un pedido en la BD")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PedidoDTO>modificar(@PathVariable Integer id, @RequestBody Pedido datos){
        PedidoDTO respuesta = this.servicio.actualizarPedido(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

}
