package com.example.EcomerceUribe.modelos;


import com.example.EcomerceUribe.ayudas.DepartamentosCliente;
import jakarta.persistence.*;

@Entity
@Table (name="clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="direccion",nullable = false, unique = false, length = 250)
    private String direccion;
    @Column(name="calificacion",nullable = false, unique = false)
    private Double calificacion;
    @Column(name="referenciaPago",nullable = false, unique = false, length = 50)
    private String referenciaPago;
    @Enumerated(EnumType.STRING)
    @Column(name="departamento", nullable = false, unique = false)
    private DepartamentosCliente departamento;
    @Column(name="ciudad",nullable = false, unique = true, length = 50)
    private String ciudad;

    public Cliente() {
    }

    public Cliente(Integer id, String direccion, Double calificacion, String referenciaPago, DepartamentosCliente departamento, String ciudad) {
        this.id = id;
        this.direccion = direccion;
        this.calificacion = calificacion;
        this.referenciaPago = referenciaPago;
        this.departamento = departamento;
        this.ciudad = ciudad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public String getReferenciaPago() {
        return referenciaPago;
    }

    public void setReferenciaPago(String referenciaPago) {
        this.referenciaPago = referenciaPago;
    }

    public DepartamentosCliente getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentosCliente departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
