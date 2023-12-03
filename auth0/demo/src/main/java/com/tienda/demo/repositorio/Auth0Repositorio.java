package com.tienda.demo.repositorio;


import com.tienda.demo.Entidad.Auth0;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Auth0Repositorio extends JpaRepository<Auth0,String> {

   Auth0 findByEmail(String email);
}
