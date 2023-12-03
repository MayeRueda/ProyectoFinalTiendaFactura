package com.example.demo.repositorio;
import com.example.demo.Entidad.pedidos;
import com.example.demo.Entidad.pedidos_productos;
import com.example.demo.Entidad.productos;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<productos,Integer> {
    List<productos> findByNomProducto(String nomProducto);
    @Query(value = "SELECT * FROM productos  WHERE cod_Pedido = :cod_Pedido", nativeQuery = true)
    List<productos> BuscarProductoPorcod_Pedido(@Param("cod_Pedido") Integer cod_Pedido);
    @Query(value = "SELECT DISTINCT pp.cod_producto, p.nom_producto, p.cod_pedido FROM pedidos_productos pp INNER JOIN productos p ON pp.cod_producto = p.cod_producto", nativeQuery = true)
    List<Object[]> ProductosConPedidos();

    @Transactional
    @Modifying
    @Query(value = "UPDATE productos SET nomProducto=:nomProducto WHERE cod_Producto=:cod_Producto", nativeQuery = true)
    void actualizarProductos(@Param("nomProducto") String nomProducto,  @Param("cod_Producto") Integer cod_Producto);


}

