package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO;
import com.example.EcomerceUribe.modelos.DTOS.UsuarioGenericoDTO;
import com.example.EcomerceUribe.servicios.ClienteServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Controlador para operaciones en la tabla clientes")

public class ClienteControlador {
    //llamar al servicio
    @Autowired
    ClienteServicio servicio;

    //listar los posibles llamados a los servicios disponibles

    //se crean funciones por cada posible llamado y se les agrega el metodo HTTP correspondiente (GET,PUT,POST, DELETE)

    //guardar
    @Operation(summary = "Crear un cliente en la BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<ClienteDTO> guardar(@RequestBody Cliente datos){
        ClienteDTO respuesta=this.servicio.guardarCliente(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    //LISTAR
    @Operation(summary = "Listar los clientes de la BD")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ClienteDTO>>listar(){
        List<ClienteDTO> respuesta=this.servicio.buscarTodosLosClientes();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //burcarPorId
    @Operation(summary = "Buscar un cliente en la BD")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ClienteDTO>buscarPorId(@PathVariable Integer id){
        ClienteDTO respuesta=this.servicio.buscarClientePorID(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //eliminar
    @Operation(summary = "Eliminar un cliente en la BD")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void>eliminar(@PathVariable Integer id){
        this.servicio.buscarClientePorID(id);
        return ResponseEntity.noContent().build();
    }

    //modificar
    @Operation(summary = "Modificar un cliente en la BD")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ClienteDTO>modificar(@PathVariable Integer id, @RequestBody Cliente datos){
        ClienteDTO respuesta = this.servicio.actualizarCliente(id,datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

}
