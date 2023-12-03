package com.tienda.demo.repositorio;
import com.tienda.demo.Entidad.comprar;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
;
import java.time.LocalTime;
import java.util.List;

public interface ComprarCrudRepository extends CrudRepository<comprar,Long> {
    List<comprar> findByMetodoPago(String metodoPago);
    @Query(value = "SELECT * FROM comprar  WHERE cc_Empleado = :cc_Empleado", nativeQuery = true)
    List<comprar> BuscarPedidoPorcc_Empleado(@Param("cc_Empleado") Long cc_Empleado);
    @Query(value = "UPDATE comprar SET total_compra=:total_compra,iva=:iva,pago=:pago,vueltas=:vueltas,dEstablecimiento=:dEstablecimiento, metodoPago=:metodoPago,fecha=:fecha, hora=:hora WHERE cod_Compra=:cod_Compra,cc_clientes=.cc_clientes, cc_Empleado=:cc_Empleado, codigodetalle=:codigodetalle", nativeQuery = true)
    void actualizarCompra(@Param("total_compra") Long total_compra, @Param("iva") String iva, @Param("pago") int pago, @Param("vueltas") int vueltas, @Param("dEstablecimiento") String dEstablecimiento, @Param("fecha") LocalDate fecha, @Param("metodoPago") String metodoPago, @Param("hora") LocalTime hora, @Param("cod_Compra") Long cod_Compra);


}

