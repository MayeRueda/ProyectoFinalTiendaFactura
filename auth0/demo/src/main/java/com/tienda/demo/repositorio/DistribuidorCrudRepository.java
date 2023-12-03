package com.tienda.demo.repositorio;


import com.tienda.demo.Entidad.distribuidores;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DistribuidorCrudRepository extends CrudRepository<distribuidores,Long> {
    List<distribuidores> findByNomDistribuidor(String nomDistribuidor);

    @Transactional
    @Modifying
    @Query(value = "UPDATE distribuidores SET nomDistribuidor=:nomDistribuidor WHERE Nit_Distribuidor=:Nit_Distribuidor", nativeQuery = true)
    void actualizarDistribuidores(@Param("nomDistribuidor") String nomDistribuidor, @Param("Nit_Distribuidor") Long Nit_Distribuidor);

}


