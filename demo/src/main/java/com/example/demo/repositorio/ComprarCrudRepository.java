package com.example.demo.repositorio;
import com.example.demo.Entidad.comprar;
import com.example.demo.Entidad.pedidos;
import com.example.demo.Entidad.productos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ComprarCrudRepository extends CrudRepository<comprar,Integer> {
    List<comprar> findByMetodoPago(String metodoPago);
    @Query(value = "SELECT * FROM comprar  WHERE cc_Empleado = :cc_Empleado", nativeQuery = true)
    List<comprar> BuscarPedidoPorcc_Empleado(@Param("cc_Empleado") Integer cc_Empleado);
    @Query(value = "UPDATE comprar SET total_compra=:total_compra,iva=:iva,pago=:pago,vueltas=:vueltas,dEstablecimiento=:dEstablecimiento, metodoPago=:metodoPago,fecha=:fecha, hora=:hora WHERE cod_Compra=:cod_Compra,cc_clientes=.cc_clientes, cc_Empleado=:cc_Empleado, codigodetalle=:codigodetalle", nativeQuery = true)
    void actualizarCompra(@Param("total_compra") int total_compra, @Param("iva") String iva, @Param("pago") int pago, @Param("vueltas") int vueltas, @Param("dEstablecimiento") String dEstablecimiento, @Param("fecha") LocalDate fecha, @Param("metodoPago") String metodoPago, @Param("hora") LocalTime hora, @Param("cod_Compra") Integer cod_Compra);


}

