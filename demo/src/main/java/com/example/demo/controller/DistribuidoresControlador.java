package com.example.demo.controller;
import com.example.demo.Entidad.clientes;
import com.example.demo.Entidad.distribuidores;
import com.example.demo.repositorio.PedidoCrudRepository;
import com.example.demo.service.ClienteServicio;
import com.example.demo.service.DistribuidorServicio;
import com.example.demo.service.PedidoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@CrossOrigin("*")
@RestController
public class DistribuidoresControlador {
    private DistribuidorServicio distribuidorServicio;
    private PedidoServicio pedidoServicio;

    public DistribuidoresControlador(DistribuidorServicio distribuidorServicio, PedidoServicio pedidoServicio) {
        this.distribuidorServicio = distribuidorServicio;
        this.pedidoServicio = pedidoServicio;
    }

    @PostMapping("/insertardis")
    public ResponseEntity<Void>insertardistribuidores(@RequestBody distribuidores Distribuidores){

        System.out.println(Distribuidores.getNitDistribuidor());
        if (distribuidorServicio.distribuidoresPorCC(Distribuidores.getNitDistribuidor())!=null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            distribuidorServicio.insertardistribuidores(Distribuidores);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
    @GetMapping("/listardis")
    public ResponseEntity<List<distribuidores>>listarDistribuidores(){
        return new ResponseEntity<>(distribuidorServicio.listaDistribuidores(), HttpStatus.OK);

    }
    @GetMapping("/unicodis/{Nit_Distribuidor}")
    public ResponseEntity<distribuidores>distribuidorPorCC(@PathVariable Integer Nit_Distribuidor){
        if (distribuidorServicio.distribuidoresPorCC(Nit_Distribuidor) != null) {
            return new ResponseEntity<>(distribuidorServicio.distribuidoresPorCC(Nit_Distribuidor),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nombredis/{nombredis}")
    public ResponseEntity<List<distribuidores>> distribuidorPorNombre(@PathVariable String nombredis) {
        return new ResponseEntity<>(distribuidorServicio.nombreDistribuidores(nombredis), HttpStatus.OK);
    }
    @DeleteMapping("/eliminardis/{nit_Distribuidor}")
    public ResponseEntity<Void> eliminarnitDistribuidor(@PathVariable Integer nit_Distribuidor) {
        if (distribuidorServicio.distribuidoresPorCC(nit_Distribuidor) != null) {
            if (pedidoServicio.buscarpedidopornitdistribuidor(nit_Distribuidor)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            else{
                distribuidorServicio.eliminarnitDistribuidor(nit_Distribuidor);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/actualizardisSave")
    public ResponseEntity<Void> actualizardisDistribuidorSave(@RequestBody distribuidores Distribuidores){
        if(distribuidorServicio.distribuidoresPorCC(Distribuidores.getNitDistribuidor())!=null){
            distribuidorServicio.insertardistribuidores(Distribuidores);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}





