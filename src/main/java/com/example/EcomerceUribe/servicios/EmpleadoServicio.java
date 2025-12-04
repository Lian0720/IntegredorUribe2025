package com.example.EcomerceUribe.servicios;


import com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO;
import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO;
import com.example.EcomerceUribe.modelos.Empleado;
import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.modelos.mapas.IEmpleadoMapa;
import com.example.EcomerceUribe.repositorios.IEmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
    //Buscar Empleados todos
    public List<EmpleadoDTO> buscarTodosLosEmpleados() {
        List<Empleado> listadeEmpleadosConsultados = this.repositorio.findAll();
        return this.mapa.convertir_list_a_listdto(listadeEmpleadosConsultados);
    }

    //Buscar Empleado por id
    public EmpleadoDTO buscarEmpleadoPorID(Integer id) {
        Optional<Empleado> EmpleadoQueEstoyBuscando = this.repositorio.findById(id);
        if (!EmpleadoQueEstoyBuscando.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningún Empleado con el id " + id + "suministrado"
            );
        }
        Empleado EmpleadoEncontrado = EmpleadoQueEstoyBuscando.get();
        return this.mapa.convertir_empleado_a_empleadodto(EmpleadoEncontrado);
    }

    //Eliminar Empleado
    public void eliminarEmpleado(Integer id) {
        Optional<Empleado> EmpleadoQueEstoyBuscando = this.repositorio.findById(id);
        if (!EmpleadoQueEstoyBuscando.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun empleado con el id " + id + "suministrado"
            );
        }
        Empleado EmpleadoEncontrado = EmpleadoQueEstoyBuscando.get();
        try {
            this.repositorio.delete(EmpleadoEncontrado);
        } catch (Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se puedo eliminar el empleado, " + error.getMessage()
            );
        }
    }

    //Modificar datos del Empleado
    public  EmpleadoDTO actualizarEmpleado(Integer id, Empleado datosActualizados) {
        Optional<Empleado> EmpleadoQueEstoyEditanto = this.repositorio.findById(id);
        if (!EmpleadoQueEstoyEditanto.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun empleado con el id " + id + "suministrado"
            );
        }
        Empleado EmpleadoEncontrado = EmpleadoQueEstoyEditanto.get();

        //Aplique validaciones sobre datos enviados desde el Frond

        //Actualizo los campos que permitieron modificar

        //Nombre //Correo

        EmpleadoEncontrado.setCargo(datosActualizados.getCargo());
        EmpleadoEncontrado.setSalario(datosActualizados.getSalario());

        //Concluyo actualizacion en la base de datos
        Empleado EmpleadoActualizado = this.repositorio.save(EmpleadoEncontrado);

        if (EmpleadoActualizado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al actualizar el empleado en la base de datos. Intentar nuevamente"
            );
        }

        return this.mapa.convertir_empleado_a_empleadodto(EmpleadoActualizado);

    }


}
