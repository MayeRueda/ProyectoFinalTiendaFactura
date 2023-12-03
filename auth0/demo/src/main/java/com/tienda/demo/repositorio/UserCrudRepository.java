package com.tienda.demo.repositorio;

import com.tienda.demo.Entidad.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCrudRepository extends JpaRepository<User, Long> {

    User findByCorreo(String correo);


    User findByNivelSoporte(String nivelSoporte);
}