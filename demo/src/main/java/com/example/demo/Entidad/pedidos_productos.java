package com.example.demo.Entidad;

import jakarta.persistence.*;

@Entity
@Table(name="pedidos_productos")

public class pedidos_productos {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod")

    private Integer cod;
    @Column(nullable = false, length = 80)
    private int cantidad;
    @Column(nullable = false, length = 80)
    private int precios;
    @ManyToOne
    @JoinColumn(name = "cod_Pedido", referencedColumnName = "cod_Pedido")


    private pedidos pedidos;

    @ManyToOne
    @JoinColumn(name = "cod_Producto", referencedColumnName = "cod_Producto")

    private productos productos;

    public pedidos_productos() {

    }

    public pedidos_productos(Integer cod, int cantidad, int precios, com.example.demo.Entidad.pedidos pedidos, com.example.demo.Entidad.productos productos) {
        this.cod = cod;
        this.cantidad = cantidad;
        this.precios = precios;
        this.pedidos = pedidos;
        this.productos = productos;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecios() {
        return precios;
    }

    public void setPrecios(int precios) {
        this.precios = precios;
    }

    public com.example.demo.Entidad.pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(com.example.demo.Entidad.pedidos pedidos) {
        this.pedidos = pedidos;
    }

    public com.example.demo.Entidad.productos getProductos() {
        return productos;
    }

    public void setProductos(com.example.demo.Entidad.productos productos) {
        this.productos = productos;
    }
}