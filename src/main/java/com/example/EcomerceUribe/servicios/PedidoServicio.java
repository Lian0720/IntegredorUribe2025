package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO;
import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO;
import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.modelos.mapas.IPedidoMapa;
import com.example.EcomerceUribe.repositorios.IPedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServicio {

    @Autowired
    private IPedidoRepositorio repositorio;

    @Autowired
    private IPedidoMapa mapa;

    public PedidoDTO guardarPedido(Pedido datosPedido) {

        //validaciones 3
        //validacion no vacia fechaCreacion
        if (datosPedido.getFechaCreacion() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La fecha de creación del pedido es obligatorio"
            );
        }
        //validacion no vacia fechaEntrega
        if (datosPedido.getFechaEntrega() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La fecha de entrega del pedido es obligatorio"
            );
        }
        //validacion no vacia costoEnvio
        if (datosPedido.getCostoEnvio() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El costo del pedido es obligatorio"
            );
        }

        //Intentar guardar el pedido
        Pedido pedidoQueGuardoElRepo = this.repositorio.save(datosPedido);
        if (pedidoQueGuardoElRepo == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el pedido en la base de datos"
            );
        }
        //Retornar el dto al controlado
        return this.mapa.convertir_pedido_a_pedidodto(pedidoQueGuardoElRepo);
    }
    //Buscar pedidos todos
    public List<PedidoDTO> buscarTodosLosPedidos() {
        List<Pedido> listadePedidosConsultados = this.repositorio.findAll();
        return this.mapa.convertir_list_a_listdto(listadePedidosConsultados);
    }

    //Buscar pedidos por id
    public PedidoDTO buscarPedidoPorID(Integer id) {
        Optional<Pedido> PedidoQueEstoyBuscando = this.repositorio.findById(id);
        if (!PedidoQueEstoyBuscando.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningún pedido con el id " + id + "suministrado"
            );
        }
        Pedido PedidoEncontrado = PedidoQueEstoyBuscando.get();
        return this.mapa.convertir_pedido_a_pedidodto(PedidoEncontrado);
    }

    //Eliminar pedidos
    public void eliminarPedido(Integer id) {
        Optional<Pedido> PedidoQueEstoyBuscando = this.repositorio.findById(id);
        if (!PedidoQueEstoyBuscando.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun pedido con el id " + id + "suministrado"
            );
        }
        Pedido PedidoEncontrado = PedidoQueEstoyBuscando.get();
        try {
            this.repositorio.delete(PedidoEncontrado);
        } catch (Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se puedo eliminar el producto, " + error.getMessage()
            );
        }
    }


    //Modificar datos del pedido
    public PedidoDTO actualizarPedido(Integer id, Pedido datosActualizados) {
        Optional<Pedido> PedidoQueEstoyEditanto = this.repositorio.findById(id);
        if (!PedidoQueEstoyEditanto.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun pedido con el id " + id + "suministrado"
            );
        }
        Pedido PedidoEncontrado = PedidoQueEstoyEditanto.get();

        //Aplique validaciones sobre datos enviados desde el Frond

        //Actualizo los campos que permitieron modificar

        //Nombre //Correo

        PedidoEncontrado.setCostoEnvio(datosActualizados.getCostoEnvio());
        PedidoEncontrado.setMontoTotal(datosActualizados.getMontoTotal());

        //Concluyo actualizacion en la base de datos
        Pedido PedidooActualizado = this.repositorio.save(PedidoEncontrado);

        if (PedidooActualizado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al actualizar el pedido en la base de datos. Intentar nuevamente"
            );
        }

        return this.mapa.convertir_pedido_a_pedidodto(PedidooActualizado);

    }

}