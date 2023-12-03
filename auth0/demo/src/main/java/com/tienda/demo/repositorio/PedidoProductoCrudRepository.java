package com.tienda.demo.repositorio;

import com.tienda.demo.Entidad.pedidos_productos;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PedidoProductoCrudRepository extends CrudRepository<pedidos_productos,Long> {
    List<pedidos_productos> findByCod(Long cod);
    @Query(value = "SELECT p.cod_Producto, p.nomProducto, p.cod_Pedido FROM productos p INNER JOIN pedidos_productos pp ON p.cod = pp.cod", nativeQuery=true)
    List<pedidos_productos> Buscarprecio(   @Param("nomProducto") String nomProducto, @Param("cod_Pedido")Long cod_Pedido,@Param("cod") Long cod,@Param("cod_Producto") Integer cod_Producto);
    @Query(value="SELECT p.precios FROM pedidos_productos p WHERE p.productos.cod_Producto=:codpedidoproducto")
    int buscarPrecioCodPedido(@Param("codpedidoproducto") Long codigo);
    @Query(value = "SELECT * FROM pedidos_productos  WHERE cod_Producto =:cod_Producto", nativeQuery = true)
    List<pedidos_productos> Buscarpedidoproductoporcod_Producto(@Param("cod_Producto") Long cod_Producto);
    @Query(value = "SELECT * FROM pedidos_productos  WHERE cod == :cod", nativeQuery = true)
    List<pedidos_productos> Buscarpedidoproductocod(@Param("cod") Long cod);
    @Query(value = "SELECT * FROM pedidos_productos  WHERE cod_Pedido = :cod_Pedido", nativeQuery = true)
    List<pedidos_productos> BuscarPedidoproductPorcod_Pedido(@Param("cod_Pedido") Long cod_Pedido);
    @Transactional
    @Modifying
    @Query(value = "UPDATE pedidos_productos SET cantidad=:cantidad,precios=:precios  WHERE cod=:cod", nativeQuery = true)
    void actualizarPedidosProductos(@Param("cantidad") int cantidad, @Param("precios")int precios, @Param("cod") Long cod);

}

