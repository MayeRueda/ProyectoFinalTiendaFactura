package com.example.demo.Entidad;

import jakarta.persistence.*;

@Entity
@Table(name="detalles")

public class detalles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_Identificacion")

    private Integer cod_Identificacion;
    @Column(nullable = false, length = 80)
    private int cantidad;
    @Column(nullable = false, length = 80)
    private int codigodetalle;
    @Column(nullable = false, length = 80)
    private int precio;
    @Column(nullable = false, length = 80)
    private int pago;






    @ManyToOne
    @JoinColumn(name = "cod", referencedColumnName = "cod")

    private pedidos_productos pedidos_productos;



    public detalles() {

    }

    public detalles(Integer cod_Identificacion, int cantidad, int codigodetalle, int precio, int pago, com.example.demo.Entidad.pedidos_productos pedidos_productos) {
        this.cod_Identificacion = cod_Identificacion;
        this.cantidad = cantidad;
        this.codigodetalle = codigodetalle;
        this.precio = precio;
        this.pago = pago;
        this.pedidos_productos = pedidos_productos;
    }

    public Integer getCod_Identificacion() {
        return cod_Identificacion;
    }

    public void setCod_Identificacion(Integer cod_Identificacion) {
        this.cod_Identificacion = cod_Identificacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCodigodetalle() {
        return codigodetalle;
    }

    public void setCodigodetalle(int codigodetalle) {
        this.codigodetalle = codigodetalle;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getPago() {
        return pago;
    }

    public void setPago(int pago) {
        this.pago = pago;
    }

    public com.example.demo.Entidad.pedidos_productos getPedidos_productos() {
        return pedidos_productos;
    }

    public void setPedidos_productos(com.example.demo.Entidad.pedidos_productos pedidos_productos) {
        this.pedidos_productos = pedidos_productos;
    }
}