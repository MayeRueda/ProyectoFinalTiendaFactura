package com.example.demo.Entidad;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name="pedidos")

public class pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_Pedido")

    private Integer cod_Pedido;

    private LocalDate fechapedido;
    private LocalTime horapedido;
    @Column(nullable = false, length = 50)

    private int can_Total;
    @Column(nullable = false, length = 80)
    private int costoNeto;
    @Column(nullable = false, length = 80)
    private int can_res;

    @ManyToOne
    @JoinColumn(name = "Nit_Distribuidor", referencedColumnName = "Nit_Distribuidor")
    private distribuidores distribuidoresA;

    public pedidos() {

    }

    public pedidos(Integer cod_Pedido, LocalDate fechapedido, LocalTime horapedido, int can_Total, int costoNeto, int can_res, distribuidores distribuidoresA) {
        this.cod_Pedido = cod_Pedido;
        this.fechapedido = fechapedido;
        this.horapedido = horapedido;
        this.can_Total = can_Total;
        this.costoNeto = costoNeto;
        this.can_res = can_res;
        this.distribuidoresA = distribuidoresA;
    }

    public Integer getCod_Pedido() {
        return cod_Pedido;
    }

    public void setCod_Pedido(Integer cod_Pedido) {
        this.cod_Pedido = cod_Pedido;
    }

    public LocalDate getFechapedido() {
        return fechapedido;
    }

    public void setFechapedido(LocalDate fechapedido) {
        this.fechapedido = fechapedido;
    }

    public LocalTime getHorapedido() {
        return horapedido;
    }

    public void setHorapedido(LocalTime horapedido) {
        this.horapedido = horapedido;
    }

    public int getCan_Total() {
        return can_Total;
    }

    public void setCan_Total(int can_Total) {
        this.can_Total = can_Total;
    }

    public int getCostoNeto() {
        return costoNeto;
    }

    public void setCostoNeto(int costoNeto) {
        this.costoNeto = costoNeto;
    }

    public int getCan_res() {
        return can_res;
    }

    public void setCan_res(int can_res) {
        this.can_res = can_res;
    }

    public distribuidores getDistribuidoresA() {
        return distribuidoresA;
    }

    public void setDistribuidoresA(distribuidores distribuidoresA) {
        this.distribuidoresA = distribuidoresA;
    }
}