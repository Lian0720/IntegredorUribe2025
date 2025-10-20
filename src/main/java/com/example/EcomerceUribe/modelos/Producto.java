package com.example.EcomerceUribe.modelos;

import com.example.EcomerceUribe.ayudas.CategoriaProductos;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false, unique = false, length = 50)
    private String nombres;
    @Column(name = "fotografia ", nullable = false, unique = false, length = 50)
    private String fotografia;
    @Column(name = "descripcion", nullable = false, unique = false, length = 250)
    private String descripcion;
    @Enumerated(EnumType.STRING)
    @Column(name="categoria",nullable = false, unique = false)
    private CategoriaProductos categoria;
    @Column(name = "precioUnitario", nullable = false, unique = false)
    private Integer precioUnitario;
    @Column(name = "marca", nullable = false, unique = false, length = 50)
    private String marca;
    @Column(name = "aplicaDescuento", nullable = false, unique = false)
    private boolean aplicaDescuento;

    @ManyToOne
    @JoinColumn(name = "fk_pedido", referencedColumnName = "id")
    @JsonBackReference(value = "relacionpedidoproducto")
    private Pedido pedido;

    public Producto() {
    }

    public Producto(Integer id, String nombres, String fotografia, String descripcion, CategoriaProductos categoria, Integer precioUnitario, String marca, boolean aplicaDescuento, Pedido pedido) {
        this.id = id;
        this.nombres = nombres;
        this.fotografia = fotografia;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precioUnitario = precioUnitario;
        this.marca = marca;
        this.aplicaDescuento = aplicaDescuento;
        this.pedido = pedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public boolean isAplicaDescuento() {
        return aplicaDescuento;
    }

    public void setAplicaDescuento(boolean aplicaDescuento) {
        this.aplicaDescuento = aplicaDescuento;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
