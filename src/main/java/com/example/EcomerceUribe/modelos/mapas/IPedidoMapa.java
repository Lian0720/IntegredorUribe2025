package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO;
import com.example.EcomerceUribe.modelos.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "string")
public interface IPedidoMapa {

    @Mapping(source = "montoTotal", target = "montoTotal")
    @Mapping(source = "fechaCreacion", target = "fechaCreacion")
    @Mapping(source = "fechaEntrega", target = "fechaEntrega")
    @Mapping(source = "costoEnvio", target = "costoEnvio")
    PedidoDTO convertir_pedido_a_pedidodto (Pedido pedido);

    List<PedidoDTO> convertir_list_a_listdto (List<Pedido> List);

}
