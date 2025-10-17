package com.example.EcomerceUribe.modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //String nombre
    //String fotografia
    //String descripcion
    //ENUM categoria(Camiseta, Pantalon, vestido, Interior, Zapato, Chaqueta,Polo,Bermuda, Accesorio)
    //Integer precioUnitario
    //String marca
    //boolean aplicaDescuento
    @ManyToOne
    @JoinColumn(name = "fk_pedido", referencedColumnName = "id")
    @JsonBackReference(value = "relacionpedidoproducto")
    private Pedido pedido;

    public Producto() {
    }

    public Producto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
