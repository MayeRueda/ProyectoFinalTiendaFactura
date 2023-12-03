package com.tienda.demo.Entidad;

import jakarta.persistence.*;

@Entity
@Table(name="productos")

public class productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_Producto")
    private Long cod_Producto;
    @Column(nullable = false, length = 50)

    private String nomProducto;

    @ManyToOne
    @JoinColumn(name = "cod_Pedido", referencedColumnName = "cod_Pedido")

    private pedidos pedidos;

    public productos() {

    }

    public productos(String nomProducto,  pedidos pedidos) {
        this.nomProducto = nomProducto;
        this.pedidos = pedidos;
    }

    public Long getCod_Producto() {
        return cod_Producto;
    }

    public void setCod_Producto(Long cod_Producto) {
        this.cod_Producto = cod_Producto;
    }

    public String getNomProducto() {
        return nomProducto;
    }

    public void setNomProducto(String nomProducto) {
        this.nomProducto = nomProducto;
    }

    public pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(pedidos pedidos) {
        this.pedidos = pedidos;
    }
}