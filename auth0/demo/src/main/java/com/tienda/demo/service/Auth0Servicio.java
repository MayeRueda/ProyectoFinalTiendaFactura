package com.tienda.demo.service;

import com.tienda.demo.Entidad.Auth0;
import com.tienda.demo.Entidad.User;
import com.tienda.demo.repositorio.Auth0Repositorio;
import com.tienda.demo.repositorio.UserCrudRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class Auth0Servicio {

    private Auth0Repositorio repositorio;
    private UserCrudRepository repoUsu;

    public Auth0Servicio(Auth0Repositorio repositorio, UserCrudRepository repoUsu) {
        this.repositorio = repositorio;
        this.repoUsu = repoUsu;
    }

    public Auth0 crear(Auth0 usuario) {
        return repositorio.save(usuario);
    }

    public Auth0 buscarEmail(String email) {
        if (repositorio.findById(email).isPresent()) {
            return repositorio.findById(email).get();
        } else {
            return null;
        }
    }

    public Auth0 getCrearUser(Map<String, Object> dataUser) {
        String email = (String) dataUser.get("email");
        Auth0 user = buscarEmail(email);

        if (user == null) {
            String name = (String) dataUser.get("nickname");
            String imag = (String) dataUser.get("picture");
            String auth_id = (String) dataUser.get("sub");

            // Utiliza repoUsu para obtener User
            User user1 = repoUsu.findByCorreo(email);

            if (user1 != null) {
                // Asigna la relación y el tipoderol al nuevo usuario
                Auth0 nuevo = new Auth0(email, name, imag, auth_id, user1, user1.getRoluser());
                return this.crear(nuevo);
            } else {
                // Si no se encuentra User, puedes manejarlo según tus necesidades
                return null;
            }
        } else {
            return user;
        }
    }
}
