package com.tienda.demo.repositorio;


import com.tienda.demo.Entidad.devoluciones;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DevolucionCrudRepository extends CrudRepository<devoluciones,Long> {
    List<devoluciones> findByUnidades(int unidades);

    @Query(value = "SELECT * FROM devoluciones  WHERE cod_Identificacion = :cod_Identificacion", nativeQuery = true)
    List<devoluciones> BuscarDetallePorcodIdentificacion(@Param("cod_Identificacion") Long cod_Identificacion);
    @Transactional
    @Modifying

    @Query(value = "UPDATE devoluciones SET unidades=:unidades WHERE cod_Devolucion=:cod_Devolucion", nativeQuery = true)
    void actualizarDevoluciones(@Param("unidades") int unidades, @Param("cod_Devolucion") Long cod_Devolucion);

}
