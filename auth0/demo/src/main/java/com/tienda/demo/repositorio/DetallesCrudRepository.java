package com.tienda.demo.repositorio;

import com.tienda.demo.Entidad.detalles;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetallesCrudRepository extends CrudRepository<detalles,Long> {

    List<detalles> findByPrecio(int precio);
    @Query(value = "SELECT d.pago FROM detalles d WHERE d.cod_Identificacion=:codigoDetalles")
    int precioDetalles(@Param("codigoDetalles") Long codigo);


    @Query(value = "SELECT * FROM detalles d WHERE d.cod = :cod", nativeQuery = true)
    List<detalles> BuscardetalleporcodPedidoProducto(@Param("cod") Long cod);
    @Transactional
    @Modifying

    @Query(value = "UPDATE detalles SET cantidad=:cantidad, precio=:precio, pago=:pago, codigodetalle=:codigodetalle WHERE cod_Identificacion=:cod_Identificacion,cod=:cod", nativeQuery = true)
    void actualizarDetalles(@Param("cantidad") int cantidad,@Param("precio") int precio,@Param("pago") int pago,@Param("codigodetalle") int codigodetalle, @Param("cod_Identificacion") Long cod_Identificacion);

}


