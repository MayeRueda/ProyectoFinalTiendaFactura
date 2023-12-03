package com.example.demo.repositorio;
import com.example.demo.Entidad.detalles;
import com.example.demo.Entidad.distribuidores;
import com.example.demo.Entidad.empleados;
import com.example.demo.Entidad.pedidos;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface PedidoCrudRepository extends CrudRepository<pedidos,Integer> {

    List<pedidos> findByCostoNeto(int costoNeto);
    @Query(value = "SELECT * FROM pedidos  WHERE nit_Distribuidor = :nit_Distribuidor", nativeQuery = true)
    List<pedidos> BuscarPedidoPornitDistribuidor(@Param("nit_Distribuidor") Integer nit_Distribuidor);
    @Transactional
    @Modifying
    @Query(value = "UPDATE pedidos SET can_Total=:can_Total,costoNeto=:costoNeto,fechapedido=:fechapedido, horapedido=:horapedido  WHERE cod_Pedido=:cod_Pedido", nativeQuery = true)
    void actualizarPedidos(@Param("can_Total") int can_Total, @Param("costoNeto")int costoNeto, @Param("fechapedido") LocalDate fechapedido, @Param("horapedido") LocalTime horapedido, @Param("cod_Pedido") Integer cod_Pedido);
    @Query(value="select p.cod_Pedido, p.can_Total, p.costoNeto, d.Nit_Distribuidor from pedidos as p inner join distribuidores as d on p.documento = e.documento inner join libro as l on p.isbn = l.isbn", nativeQuery = true)
    List<Object[]> findDatosPrestamo();

    @Query(value="select p.cod_Pedido, p.can_Total, p.costoNeto, d.Nit_Distribuidor from pedidos as p inner join distribuidores as d on p.documento = e.documento inner join libro as l on p.isbn = l.isbn where p.documento= :est", nativeQuery = true)
    List<Object[]> findEstudiantePrestamo(String est);

}


