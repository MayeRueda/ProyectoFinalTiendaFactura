package com.example.demo.controller;

import com.example.demo.Entidad.detalles;
import com.example.demo.Entidad.devoluciones;
import com.example.demo.Entidad.pedidos_productos;
import com.example.demo.Entidad.productos;
import com.example.demo.service.DevolucionServicio;
import com.example.demo.service.ProductoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
public class DevolucionControlador {
    private DevolucionServicio devolucionServicio;

    public DevolucionControlador(DevolucionServicio devolucionServicio) {
        this.devolucionServicio = devolucionServicio;
    }


    @GetMapping("/listardevoluciones")
    public ResponseEntity<List<devoluciones>>listardevoluciones(){
        return new ResponseEntity<>(devolucionServicio.listardevoluciones(), HttpStatus.OK);

    }
    @GetMapping("/unicodevol/{cod_Devolucion}")
    public ResponseEntity<devoluciones>devolucionPorCD(@PathVariable Integer cod_Devolucion){
        if(devolucionServicio.devolucionPorCD(cod_Devolucion)!=null){
            return new ResponseEntity<>(devolucionServicio.devolucionPorCD(cod_Devolucion),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/unicodevoluciones/{unidades}")
    public  ResponseEntity <List<devoluciones>> devolucionporUnidad(@PathVariable ("unidades") int unidades){
        return  new ResponseEntity<>(devolucionServicio.devolucionporUnidad(unidades),HttpStatus.OK);
    }
    @PostMapping("/agregarDevolucion")
    public String agregarDevolucion(@RequestBody devoluciones Devoluciones ){
        return devolucionServicio.agregarDevolucion(Devoluciones);
    }
    @PutMapping("/actualizarDevoluciones")
    public ResponseEntity<Void> actualizarDevoluciones(@RequestBody devoluciones Devoluciones){
        if(devolucionServicio.devolucionPorCD(Devoluciones.getCod_Devolucion())!=null){
            devolucionServicio.actualizarDevoluciones(Devoluciones);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping("/eliminardevolucion/{cod_Devolucion}")
    public ResponseEntity<Void> eliminardevolucion(@PathVariable Integer cod_Devolucion){
        if(devolucionServicio.devolucionPorCD(cod_Devolucion)!=null){
            devolucionServicio.eliminarDevolucion(cod_Devolucion);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}






