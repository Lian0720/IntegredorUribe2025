package com.example.EcomerceUribe.servicios;


import com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO;
import com.example.EcomerceUribe.modelos.Empleado;
import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.modelos.mapas.IEmpleadoMapa;
import com.example.EcomerceUribe.repositorios.IEmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmpleadoServicio {

    @Autowired
    private IEmpleadoRepositorio repositorio;

    @Autowired
    private IEmpleadoMapa mapa;

    public EmpleadoDTO guardarEmpleado (Empleado datosEmpleado) {

        //validaciones 2
        //validacion no vacia cargo
        if (datosEmpleado.getCargo()==null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El cargo del empleado es obligatorio"
            );
    }
        //validacion no vacia sede
        if (datosEmpleado.getSede()==null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La sede del empleado es obligatoria"
            );
        }
        //Intentar guardar el empleado
        Empleado empleadoQueGuardoElRepo = this.repositorio.save(datosEmpleado);
        if (empleadoQueGuardoElRepo == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el empleado en la base de datos"
            );
        }
        //Retornar el dto al controlado
        return this.mapa.convertir_empleado_a_empleadodto(empleadoQueGuardoElRepo);
    }
}
