package com.example.EcomerceUribe.modelos.DTOS;

import com.example.EcomerceUribe.ayudas.CategoriaProductos;

public class ProductoDTO {
    private String nombres;
    private String descripcion;
    private CategoriaProductos categoria;
    private Integer precioUnitario;

    public ProductoDTO() {
    }

    public ProductoDTO(String nombres, String descripcion, CategoriaProductos categoria, Integer precioUnitario) {
        this.nombres = nombres;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precioUnitario = precioUnitario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CategoriaProductos getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProductos categoria) {
        this.categoria = categoria;
    }

    public Integer getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Integer precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}


