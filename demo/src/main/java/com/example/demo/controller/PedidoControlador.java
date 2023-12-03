package com.example.demo.controller;
import com.example.demo.Entidad.detalles;
import com.example.demo.Entidad.empleados;
import com.example.demo.Entidad.pedidos;
import com.example.demo.Entidad.pedidos_productos;
import com.example.demo.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("*")
public class PedidoControlador {
    private PedidoServicio pedidoServicio;
    private PedidoProductoServicio pedidoProductoServicio;
    private ProductoServicio productoServicio;

    private DetallesServicio detallesServicio;

    public PedidoControlador(PedidoServicio pedidoServicio, PedidoProductoServicio pedidoProductoServicio, ProductoServicio productoServicio, DetallesServicio detallesServicio) {
        this.pedidoServicio = pedidoServicio;
        this.pedidoProductoServicio = pedidoProductoServicio;
        this.productoServicio = productoServicio;
        this.detallesServicio = detallesServicio;
    }

    @GetMapping("/listarpd")
    public ResponseEntity<List<pedidos>>listarpedidos(){
        return new ResponseEntity<>(pedidoServicio.listarpedidos(), HttpStatus.OK);

    }
    @GetMapping("/unicopd/{cod_Pedido}")
    public ResponseEntity<pedidos>pedidosPorCD(@PathVariable Integer cod_Pedido){
        if(pedidoServicio.pedidosPorCD(cod_Pedido)!=null){
            return new ResponseEntity<>(pedidoServicio.pedidosPorCD(cod_Pedido),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/unicocs/{pedidocosto}")
    public  ResponseEntity<List<pedidos>> PedidoPorCosto(@PathVariable ("pedidocosto") int costoNeto){
        return  new ResponseEntity<>(pedidoServicio.PedidoPorCosto(costoNeto),HttpStatus.OK);

    }

    @PostMapping("/agregarPedidos")
    public String agregarPedidos(@RequestBody pedidos Pedidos ){
        return pedidoServicio.agregarPedidos(Pedidos);
    }
    @PutMapping("/actualizarPedidos")
    public ResponseEntity<Void> actualizarPedidos(@RequestBody pedidos Pedidos){
        if(pedidoServicio.pedidosPorCD(Pedidos.getCod_Pedido())!=null){
            pedidoServicio.actualizarPedidos(Pedidos);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/eliminarpedidoc/{cod_Pedido}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Integer cod_Pedido) {
        if (pedidoServicio.pedidosPorCD(cod_Pedido) != null) {
            if (productoServicio.buscarproductoPorcod_Pedido(cod_Pedido)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            if (pedidoProductoServicio.buscarpedidoproductPorcod_Pedido(cod_Pedido)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }else{
                pedidoServicio.eliminarPedido(cod_Pedido);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}






