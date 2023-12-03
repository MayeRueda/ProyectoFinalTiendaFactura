package com.example.demo.Entidad;

import jakarta.persistence.*;

@Entity
@Table(name="devoluciones")

public class devoluciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_Devolucion")

    private Integer cod_Devolucion;
    @Column(nullable = false, length = 80)
    private int unidades;

    @ManyToOne
    @JoinColumn(name = "cod_Identificacion", referencedColumnName = "cod_Identificacion")

    private detalles detalles;


    public devoluciones() {
    }

    public devoluciones(Integer cod_Devolucion, int unidades, com.example.demo.Entidad.detalles detalles) {

        this.cod_Devolucion = cod_Devolucion;
        this.unidades = unidades;
        this.detalles = detalles;
    }

    public Integer getCod_Devolucion() {
        return cod_Devolucion;
    }

    public void setCod_Devolucion(Integer cod_Devolucion) {
        this.cod_Devolucion = cod_Devolucion;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public com.example.demo.Entidad.detalles getDetalles() {
        return detalles;
    }

    public void setDetalles(com.example.demo.Entidad.detalles detalles) {
        this.detalles = detalles;
    }
}