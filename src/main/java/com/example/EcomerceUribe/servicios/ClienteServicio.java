package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO;
import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO;
import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.modelos.mapas.IClienteMapa;
import com.example.EcomerceUribe.repositorios.IClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
    //Buscar Cliente todos
    public List<ClienteDTO> buscarTodosLosClientes() {
        List<Cliente> listadeClientesConsultados = this.repositorio.findAll();
        return this.mapa.convertir_List_a_listdto(listadeClientesConsultados);
    }

    //Buscar Cliente por id
    public ClienteDTO buscarClientePorID(Integer id) {
        Optional<Cliente> ClienteQueEstoyBuscando = this.repositorio.findById(id);
        if (!ClienteQueEstoyBuscando.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningún cliente con el id " + id + "suministrado"
            );
        }
        Cliente ClienteEncontrado = ClienteQueEstoyBuscando.get();
        return this.mapa.convertir_cliente_a_clientedto(ClienteEncontrado);
    }

    //Eliminar Cliente
    public void eliminarCliente(Integer id) {
        Optional<Cliente> ClienteQueEstoyBuscando = this.repositorio.findById(id);
        if (!ClienteQueEstoyBuscando.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun cliente con el id " + id + "suministrado"
            );
        }
        Cliente ClienteEncontrado = ClienteQueEstoyBuscando.get();
        try {
            this.repositorio.delete(ClienteEncontrado);
        } catch (Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se puedo eliminar el cliente, " + error.getMessage()
            );
        }
    }

    //Modificar datos del Cliente
    public ClienteDTO actualizarCliente(Integer id, Cliente datosActualizados) {
        Optional<Cliente> ClienteQueEstoyEditanto = this.repositorio.findById(id);
        if (!ClienteQueEstoyEditanto.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun cliente con el id " + id + "suministrado"
            );
        }
        Cliente ClienteEncontrado = ClienteQueEstoyEditanto.get();

        //Aplique validaciones sobre datos enviados desde el Frond

        //Actualizo los campos que permitieron modificar

        //Nombre //Correo

        ClienteEncontrado.setCiudad(datosActualizados.getCiudad());
        ClienteEncontrado.setDireccion(datosActualizados.getDireccion());

        //Concluyo actualizacion en la base de datos
        Cliente ClienteActualizado = this.repositorio.save(ClienteEncontrado);

        if (ClienteActualizado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al actualizar el cliente en la base de datos. Intentar nuevamente"
            );
        }

        return this.mapa.convertir_cliente_a_clientedto(ClienteActualizado);

    }



}
