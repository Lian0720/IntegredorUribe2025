package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IClienteMapa {

    @Mapping(source = "direccion", target = "direccion")
    @Mapping(source = "departamento", target = "departamento")
    @Mapping(source = "ciudad", target = "ciudad")
    ClienteDTO convertir_cliente_a_clientedto (Cliente cliente);

    List<ClienteDTO> convertir_List_a_listdto (List<Cliente> list);
}
