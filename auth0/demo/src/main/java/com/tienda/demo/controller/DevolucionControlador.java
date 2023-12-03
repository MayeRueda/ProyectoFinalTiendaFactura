package com.tienda.demo.controller;

import com.tienda.demo.Entidad.devoluciones;
import com.tienda.demo.service.DevolucionServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/devolucion")
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
    public ResponseEntity<devoluciones>devolucionPorCD(@PathVariable Long cod_Devolucion){
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
    public ResponseEntity<Void> eliminardevolucion(@PathVariable Long cod_Devolucion){
        if(devolucionServicio.devolucionPorCD(cod_Devolucion)!=null){
            devolucionServicio.eliminarDevolucion(cod_Devolucion);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}






