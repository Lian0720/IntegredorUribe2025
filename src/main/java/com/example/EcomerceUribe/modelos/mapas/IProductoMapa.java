package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO;
import com.example.EcomerceUribe.modelos.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "string")
public interface IProductoMapa {

    @Mapping(source = "nombres", target = "nombres")
    @Mapping(source = "descripcion", target = "descripcion")
    @Mapping(source = "categoria", target = "categoria")
    @Mapping(source = "precioUnitario", target = "precioUnitario")
    ProductoDTO convertir_producto_a_productodto (Producto producto);

    List<ProductoDTO> convertir_list_a_listdto(List<Producto> List);

}
