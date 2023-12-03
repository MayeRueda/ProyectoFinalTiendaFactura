package com.tienda.demo.service;

import com.tienda.demo.Entidad.User;
import com.tienda.demo.repositorio.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServicio {

    private final UserCrudRepository usuarioRepository;

    @Autowired
    public UserServicio(UserCrudRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean existeUsuario(int usuarioId) {
        Optional<User> usuarioOptional = usuarioRepository.findById(Long.valueOf(Long.valueOf(usuarioId)));
        return usuarioOptional.isPresent();
    }

    public List<User> findAll() {
        return usuarioRepository.findAll();
    }
    public Optional<User> findById(Long usuarioid) {
        return usuarioRepository.findById(Long.valueOf(usuarioid));
    }


    public User save(User user) {
        return usuarioRepository.save(user);
    }

    public void delete(int id) {
        usuarioRepository.deleteById(Long.valueOf(id));
    }

    public User actualizarUsuario(int id, User newUser) {
        Optional<User> usuarioExistente = usuarioRepository.findById(Long.valueOf(Long.valueOf(id)));

        if (usuarioExistente.isPresent()) {
            User user = usuarioExistente.get();
            user.setNombre(newUser.getNombre());
            user.setApellido(newUser.getApellido());
            user.setDocumento(newUser.getDocumento());
            user.setCorreo(newUser.getCorreo());
            user.setTelefono(newUser.getTelefono());
            user.setRoluser(newUser.getRoluser());
            user.setNivelSoporte(newUser.getNivelSoporte());
            return usuarioRepository.save(user);
        } else {
            throw new NoSuchElementException("No se encontró usuario ID: " + id);
        }
    }

    public boolean existsById(int id) {
        // Lógica para verificar si un usuario existe por su ID
        return usuarioRepository.existsById(Long.valueOf(id));
    }

    public User findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }
}
