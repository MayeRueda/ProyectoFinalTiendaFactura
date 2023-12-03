package com.example.demo.controller;

import com.tienda.demo.Entidad.detalles;
import com.tienda.demo.service.DetallesServicio;
import com.tienda.demo.service.DevolucionServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/detalle")
public class DetallesControlador {
    private DetallesServicio detallesServicio;
    private DevolucionServicio devolucionServicio;


    public DetallesControlador(DetallesServicio detallesServicio, DevolucionServicio devolucionServicio) {
        this.detallesServicio = detallesServicio;
        this.devolucionServicio = devolucionServicio;
    }

    @GetMapping("/listardetalles")
    public ResponseEntity<List<detalles>>listardetalles(){
        return new ResponseEntity<>(detallesServicio.listardetalles(), HttpStatus.OK);

    }
    @GetMapping("/unicodetallecod/{cod_Identificacion}")
    public ResponseEntity<detalles>detallesPorCD(@PathVariable Long cod_Identificacion){
        if(detallesServicio.detallesPorCD(cod_Identificacion)!=null){
            return new ResponseEntity<>(detallesServicio.detallesPorCD(cod_Identificacion),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/unicodetallesprecio/{precio}")
    public  ResponseEntity <List<detalles>> DetallesPorPrecio(@PathVariable ("precio") int precio){
        return  new ResponseEntity<>(detallesServicio.DetallesPorPrecio(precio),HttpStatus.OK);
    }
    @GetMapping("preciodetalles/{codigoDetalle}")
    public ResponseEntity<Integer> precioDetalle(@PathVariable("codigoDetalle") Long codigoDetalle){
        return new ResponseEntity<>(detallesServicio.precioDetalle(codigoDetalle), HttpStatus.OK);
    }
    @PostMapping("/agregarDetalle")
    public String agregarDetalles(@RequestBody detalles detalle ){
        return detallesServicio.agregarDetalles(detalle);
    }

    @PutMapping("/actualizarDetalles")
    public ResponseEntity<Void> actualizarDetalles(@RequestBody detalles Detalles){
        if(detallesServicio.detallesPorCD(Detalles.getCod_Identificacion())!=null){
            detallesServicio.actualizarDetalles(Detalles);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping("/eliminardetalle/{cod_Identificacion}")
    public ResponseEntity<Void> eliminardetalle(@PathVariable Long cod_Identificacion) {
        if (detallesServicio.detallesPorCD(cod_Identificacion) != null) {
            if (devolucionServicio.buscarcodidentificaciondetalles(cod_Identificacion)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
          else{
                detallesServicio.eliminardetalle(cod_Identificacion);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}







