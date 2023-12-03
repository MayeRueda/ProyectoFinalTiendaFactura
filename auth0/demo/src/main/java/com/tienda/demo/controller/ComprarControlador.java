package com.tienda.demo.controller;

import com.tienda.demo.Entidad.comprar;
import com.tienda.demo.service.ComprarServicio;
import com.tienda.demo.service.DetallesServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/comprar")
public class ComprarControlador {
    private ComprarServicio comprarServicio;
    private DetallesServicio detallesServicio;


    public ComprarControlador(ComprarServicio comprarServicio, DetallesServicio detallesServicio) {
        this.comprarServicio = comprarServicio;
        this.detallesServicio = detallesServicio;
    }

    @GetMapping("/listarcomprar")
    public ResponseEntity<List<comprar>>listarcomprar(){
        return new ResponseEntity<>(comprarServicio.listarcomprar(), HttpStatus.OK);

    }

    @GetMapping("/unicocomprarcod/{cod_Compra}")
    public ResponseEntity<comprar>comprarPorCD(@PathVariable Long cod_Compra){
        if(comprarServicio.comprarPorCD(cod_Compra)!=null){
            return new ResponseEntity<>(comprarServicio.comprarPorCD(cod_Compra),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/unicocomprarmetodo/{metodoPago}")
    public  ResponseEntity <List<comprar>> ComprarPorMetodopago(@PathVariable ("metodoPago") String metodoPago){
        return  new ResponseEntity<>(comprarServicio.ComprarPorMetodopago(metodoPago),HttpStatus.OK);
    }
    @PostMapping("/agregarCompras")
    public String agregarCompras(@RequestBody comprar Compras ){
        return comprarServicio.agregarCompras(Compras);
    }
    @PutMapping("/actualizarCompras")
    public ResponseEntity<Void> actualizarCompra(@RequestBody comprar Comprar){
        if(comprarServicio.comprarPorCD(Comprar.getCod_Compra())!=null){
            comprarServicio.actualizarCompra(Comprar);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping("/eliminarcompra/{cod_Compra}")
    public ResponseEntity<Void> eliminarcomprar(@PathVariable Long cod_Compra) {
        if (comprarServicio.comprarPorCD(cod_Compra) != null) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
          else{
                comprarServicio.eliminarcomprar(cod_Compra);
                return new ResponseEntity<>(HttpStatus.OK);
            }

    }
        }









