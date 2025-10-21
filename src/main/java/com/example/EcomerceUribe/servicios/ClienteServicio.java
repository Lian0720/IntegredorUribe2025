package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO;
import com.example.EcomerceUribe.modelos.Empleado;
import com.example.EcomerceUribe.modelos.mapas.IClienteMapa;
import com.example.EcomerceUribe.repositorios.IClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteServicio {

    @Autowired
    private IClienteRepositorio repositorio;

    @Autowired
    private IClienteMapa mapa;

    public ClienteDTO guardarCliente (Cliente datosCliente) {

        //validaciones 3
        //validacion no vacia direccion
        if (datosCliente.getDireccion()==null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La dirección del cliente es obligatoria"
            );
        }
        //validacion no vacia departamento
        if (datosCliente.getDepartamento()==null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El departamento del cliente es obligatorio"
            );
    }
        //validacion no vacia ciudad
        if (datosCliente.getCiudad()==null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La ciudad del cliente es obligatoria"
            );
        }
        //Intentar guardar el cliente
        Cliente clienteQueGuardoElRepo = this.repositorio.save(datosCliente);
        if (clienteQueGuardoElRepo == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el cliente en la base de datos"
            );
        }
        //Retornar el dto al controlado
        return this.mapa.convertir_cliente_a_clientedto(clienteQueGuardoElRepo);
    }
}
