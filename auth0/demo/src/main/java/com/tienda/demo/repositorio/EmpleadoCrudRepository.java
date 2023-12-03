package com.tienda.demo.repositorio;
import com.tienda.demo.Entidad.detalles;
import com.tienda.demo.Entidad.empleados;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpleadoCrudRepository extends CrudRepository<empleados,Long> {
    List<empleados> findByNomEmpleado(String Nom_Empleado);
    @Query(value = "SELECT * FROM empleados  WHERE cod_Compra = :codCompra", nativeQuery = true)
    List<detalles> BuscarDetallePorEmpleaods(@Param("codCompra") Long codCompra);

    @Query(value = "SELECT e.cc_empleado, e.cargo, e.horario, e.nom_empleado, e.salario_empleado, c.total_compra FROM empleados e INNER JOIN comprar c ON e.cc_empleado = c.cc_empleado", nativeQuery = true)
    List<Object[]> empleadosConComprar();
    @Transactional
    @Modifying
    @Query(value = "UPDATE empleados SET Nom_Empleado=:Nom_Empleado,Salario_Empleado=:Salario_Empleado,Horario=:Horario,Cargo=:Cargo  WHERE cc_Empleado=:cc_Empleado", nativeQuery = true)
    void actualizarEmpleados(@Param("Nom_Empleado") String Nom_Empleado, @Param("Salario_Empleado")int Salario_Empleado,@Param("Horario") String Horario,@Param("Cargo") String Cargo,@Param("cc_Empleado") Long cc_Empleado);

}


