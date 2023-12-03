package com.tienda.demo.Entidad;

import jakarta.persistence.*;

@Entity
public class clientes {
    @Id
    private Long cc_clientes;
    @Column(nullable = false, length = 100)
    private String nomCliente;
    @Column(nullable = false, length = 100)
    private String telefono;
    @Column(nullable = false, length = 100)
    private String correo;

    public clientes(Long cc_clientes, String nomCliente, String telefono, String correo) {
        this.cc_clientes = cc_clientes;
        this.nomCliente = nomCliente;
        this.telefono = telefono;
        this.correo = correo;
    }

    public clientes() {
    }

    public Long getCc_clientes() {
        return cc_clientes;
    }

    public void setCc_clientes(Long cc_clientes) {
        this.cc_clientes = cc_clientes;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}