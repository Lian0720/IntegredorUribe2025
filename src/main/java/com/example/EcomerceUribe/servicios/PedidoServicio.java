package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO;
import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.modelos.mapas.IPedidoMapa;
import com.example.EcomerceUribe.repositorios.IPedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
}