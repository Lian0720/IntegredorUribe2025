package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO;
import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO;
import com.example.EcomerceUribe.modelos.DTOS.UsuarioGenericoDTO;
import com.example.EcomerceUribe.modelos.Empleado;
import com.example.EcomerceUribe.servicios.EmpleadoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@Tag(name = "Controlador para operaciones en la tabla empleados")

public class EmpleadoControlador {
    //llamar al servicio
    @Autowired
    EmpleadoServicio servicio;

    //listar los posibles llamados a los servicios disponibles

    //se crean funciones por cada posible llamado y se les agrega el metodo HTTP correspondiente (GET,PUT,POST, DELETE)

    //guardar
    @Operation(summary = "Crear un empleado en la BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<EmpleadoDTO> guardar(@RequestBody Empleado datos){
        EmpleadoDTO respuesta=this.servicio.guardarEmpleado(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    //LISTAR
    @Operation(summary = "Listar los empleados de la BD")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<EmpleadoDTO>>listar(){
        List<EmpleadoDTO> respuesta=this.servicio.buscarTodosLosEmpleados();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //burcarPorId
    @Operation(summary = "Buscar un empleado en la BD")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<EmpleadoDTO>buscarPorId(@PathVariable Integer id){
        EmpleadoDTO respuestta=this.servicio.buscarEmpleadoPorID(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuestta);
    }

    //eliminar
    @Operation(summary = "Eliminar un empleado en la BD")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void>eliminar(@PathVariable Integer id){
        this.servicio.buscarEmpleadoPorID(id);
        return ResponseEntity.noContent().build();
    }

    //modificar
    @Operation(summary = "Modificar un empleado en la BD")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<EmpleadoDTO>modificar(@PathVariable Integer id, @RequestBody Empleado datos){
        EmpleadoDTO respuesta = this.servicio.actualizarEmpleado(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

}
