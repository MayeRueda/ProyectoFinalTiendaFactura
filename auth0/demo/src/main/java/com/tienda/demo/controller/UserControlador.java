package com.tienda.demo.controller;

import com.tienda.demo.Entidad.User;
import com.tienda.demo.service.UserServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@RestController
@RequestMapping("/api/usuario")
@CrossOrigin("*")
public class UserControlador {



    private final UserServicio servicioUser;

    @Autowired
    public UserControlador(UserServicio servicioUser) {
        this.servicioUser = servicioUser;
    }


    @PostMapping("/crear")
    public ResponseEntity<?> save(@Validated @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        User userSaved = servicioUser.save(user);
        return new ResponseEntity<>("Se ha creado el user correctamente", HttpStatus.CREATED);
    }

    @GetMapping("/buscarporid/{id}")
    public ResponseEntity<User> findById(@PathVariable Long usuarioid) {
        Optional<User> usuario = servicioUser.findById(usuarioid);
        return usuario.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(servicioUser.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long usuarioid) {
        Optional<User> usuario = servicioUser.findById(usuarioid);
        if (usuario.isPresent()) {
            servicioUser.delete(Math.toIntExact(usuarioid));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable int id) {
        try {
            User userSaved = servicioUser.actualizarUsuario(id, user);
            return new ResponseEntity<>(userSaved, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


