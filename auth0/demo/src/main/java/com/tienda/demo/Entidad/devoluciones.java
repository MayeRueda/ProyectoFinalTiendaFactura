package com.tienda.demo.Entidad;

import jakarta.persistence.*;

@Entity
@Table(name="devoluciones")

public class devoluciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_Devolucion")

    private Long cod_Devolucion;
    @Column(nullable = false, length = 80)
    private int unidades;

    @ManyToOne
    @JoinColumn(name = "cod_Identificacion", referencedColumnName = "cod_Identificacion")

    private detalles detalles;


    public devoluciones() {
    }

    public devoluciones(Long cod_Devolucion, int unidades, detalles detalles) {
        this.cod_Devolucion = cod_Devolucion;
        this.unidades = unidades;
        this.detalles = detalles;
    }

    public Long getCod_Devolucion() {
        return cod_Devolucion;
    }

    public void setCod_Devolucion(Long cod_Devolucion) {
        this.cod_Devolucion = cod_Devolucion;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public detalles getDetalles() {
        return detalles;
    }

    public void setDetalles(detalles detalles) {
        this.detalles = detalles;
    }
}