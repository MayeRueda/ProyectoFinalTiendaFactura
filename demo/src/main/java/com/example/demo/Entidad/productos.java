package com.example.demo.Entidad;

import jakarta.persistence.*;

@Entity
@Table(name="productos")

public class productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_Producto")
    private Integer cod_Producto;
    @Column(nullable = false, length = 50)

    private String nomProducto;

    @ManyToOne
    @JoinColumn(name = "cod_Pedido", referencedColumnName = "cod_Pedido")

    private pedidos pedidos;

    public productos() {

    }

    public productos(String nomProducto, com.example.demo.Entidad.pedidos pedidos) {
        this.nomProducto = nomProducto;
        this.pedidos = pedidos;
    }

    public Integer getCod_Producto() {
        return cod_Producto;
    }

    public void setCod_Producto(Integer cod_Producto) {
        this.cod_Producto = cod_Producto;
    }

    public String getNomProducto() {
        return nomProducto;
    }

    public void setNomProducto(String nomProducto) {
        this.nomProducto = nomProducto;
    }

    public com.example.demo.Entidad.pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(com.example.demo.Entidad.pedidos pedidos) {
        this.pedidos = pedidos;
    }
}