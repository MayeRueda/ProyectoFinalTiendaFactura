package com.example.demo.repositorio;
import com.example.demo.Entidad.detalles;
import com.example.demo.Entidad.empleados;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.ObjectError;

import java.util.List;

public interface EmpleadoCrudRepository extends CrudRepository<empleados,Integer> {
    List<empleados> findByNomEmpleado(String Nom_Empleado);
    @Query(value = "SELECT * FROM empleados  WHERE cod_Compra = :codCompra", nativeQuery = true)
    List<detalles> BuscarDetallePorEmpleaods(@Param("codCompra") Integer codCompra);

    @Query(value = "SELECT e.cc_empleado, e.cargo, e.horario, e.nom_empleado, e.salario_empleado, c.total_compra FROM empleados e INNER JOIN comprar c ON e.cc_empleado = c.cc_empleado", nativeQuery = true)
    List<Object[]> empleadosConComprar();
    @Transactional
    @Modifying
    @Query(value = "UPDATE empleados SET Nom_Empleado=:Nom_Empleado,Salario_Empleado=:Salario_Empleado,Horario=:Horario,Cargo=:Cargo  WHERE cc_Empleado=:cc_Empleado", nativeQuery = true)
    void actualizarEmpleados(@Param("Nom_Empleado") String Nom_Empleado, @Param("Salario_Empleado")int Salario_Empleado,@Param("Horario") String Horario,@Param("Cargo") String Cargo,@Param("cc_Empleado") Integer cc_Empleado);

}


