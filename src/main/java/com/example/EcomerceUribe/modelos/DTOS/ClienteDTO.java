package com.example.EcomerceUribe.modelos.DTOS;

import com.example.EcomerceUribe.ayudas.DepartamentosCliente;

public class ClienteDTO {

    private String direccion;
    private DepartamentosCliente departamento;
    private String ciudad;

    public ClienteDTO() {
    }

    public ClienteDTO(String direccion, DepartamentosCliente departamento, String ciudad) {
        this.direccion = direccion;
        this.departamento = departamento;
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
