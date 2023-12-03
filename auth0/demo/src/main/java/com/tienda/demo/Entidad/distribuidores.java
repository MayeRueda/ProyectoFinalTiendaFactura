package com.tienda.demo.Entidad;

import jakarta.persistence.*;
@Entity
@Table(name = "distribuidores")

public class distribuidores {
    @Id
    @Column(name = "Nit_Distribuidor")

    private Long nitDistribuidor;
    @Column(nullable = false, length = 100)
    private String nomDistribuidor;


    public distribuidores() {
    }

    public distribuidores(Long nitDistribuidor, String nomDistribuidor) {
        this.nitDistribuidor = nitDistribuidor;
        this.nomDistribuidor = nomDistribuidor;
    }

    public Long getNitDistribuidor() {
        return nitDistribuidor;
    }

    public void setNitDistribuidor(Long nitDistribuidor) {
        this.nitDistribuidor = nitDistribuidor;
    }

    public String getNomDistribuidor() {
        return nomDistribuidor;
    }

    public void setNomDistribuidor(String nomDistribuidor) {
        this.nomDistribuidor = nomDistribuidor;
    }
}
