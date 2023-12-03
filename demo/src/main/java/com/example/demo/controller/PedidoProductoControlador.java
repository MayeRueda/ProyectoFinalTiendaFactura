package com.example.demo.controller;

import com.example.demo.Entidad.detalles;
import com.example.demo.Entidad.pedidos;
import com.example.demo.Entidad.pedidos_productos;
import com.example.demo.Entidad.productos;
import com.example.demo.service.PedidoProductoServicio;
import com.example.demo.service.ProductoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
public class PedidoProductoControlador {
    private PedidoProductoServicio pedidoProductoServicio;

    public PedidoProductoControlador(PedidoProductoServicio pedidoProductoServicio) {
        this.pedidoProductoServicio = pedidoProductoServicio;
    }


    @GetMapping("/listarprodcpds")
    public ResponseEntity<List<pedidos_productos>> listarpedidosproductos() {
        return new ResponseEntity<>(pedidoProductoServicio.listarpedidosproductos(), HttpStatus.OK);

    }
    @GetMapping("/precioproducto/{codigo}")
    public ResponseEntity<?> precioproducto(@PathVariable int codigo){
        return new ResponseEntity<>(pedidoProductoServicio.buscarPrecioCodPedido(codigo), HttpStatus.OK);
    }

    @GetMapping("/unicoprodcpedido/{cod}")
    public ResponseEntity<pedidos_productos> pedidosproductosPorCD(@PathVariable Integer cod) {

        if(pedidoProductoServicio.pedidosproductosPorCD(cod)!=null){
            return new ResponseEntity<>(pedidoProductoServicio.pedidosproductosPorCD(cod), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
        @PostMapping("/agregarPedidosProductos")
        public String agregarPedidosProductos(@RequestBody pedidos_productos pedidoProducto ){
            return pedidoProductoServicio.agregarPedidosProductos(pedidoProducto);
        }
    @PutMapping("/actualizarPedidosProductos")

    public ResponseEntity<Void> actualizarPedidosProductos(@RequestBody pedidos_productos PedidosProductos){
        if(pedidoProductoServicio.pedidosproductosPorCD(PedidosProductos.getCod())!=null){
            pedidoProductoServicio.actualizarPedidosProductos(PedidosProductos);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping("/eliminarpedidoproductom/{cod}")
    public ResponseEntity<Void> eliminarPedidosProductos(@PathVariable Integer cod){
        if(pedidoProductoServicio.pedidosproductosPorCD(cod)!=null){
            pedidoProductoServicio.eliminarPedidosProductos(cod);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    }





