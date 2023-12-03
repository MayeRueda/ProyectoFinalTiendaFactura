package com.example.demo.repositorio;

import com.example.demo.Entidad.clientes;
import com.example.demo.Entidad.detalles;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteCrudRepository extends CrudRepository<clientes,Integer> {
   List<clientes> findByNomCliente(String nomCliente);
 @Query(value = "SELECT * FROM clientes  WHERE cod_Compra == :codCompra", nativeQuery = true)
 List<detalles> BuscarDetalleEnClientes(@Param("codCompra") Integer codCompra);

    @Query(value="select cc_clientes,nomCliente from clientes,detalles where pago>5000 group by cc_clientes;", nativeQuery = true)
    List<Object[]> findDatosClientes();
    @Transactional
    @Modifying

    @Query(value = "UPDATE clientes SET nomCliente=:nomCliente WHERE cc_clientes=:cc_clientes", nativeQuery = true)
    void actualizarClientes(@Param("nomCliente") String nomCliente, @Param("cc_clientes") Integer cc_clientes);
    @Query(value = "SELECT c.cc_clientes, c.nom_cliente, c.correo, c.telefono, co.total_compra FROM clientes c INNER JOIN comprar co ON c.cc_clientes = co.cc_clientes", nativeQuery = true)
    List<Object[]> clientesPorCompra();

}


