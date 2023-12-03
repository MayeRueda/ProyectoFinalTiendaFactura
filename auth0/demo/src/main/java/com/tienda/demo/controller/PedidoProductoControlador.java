package com.tienda.demo.controller;

import com.tienda.demo.Entidad.pedidos_productos;
import com.tienda.demo.service.PedidoProductoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/pedido_producto")
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
    public ResponseEntity<?> precioproducto(@PathVariable Long codigo){
        return new ResponseEntity<>(pedidoProductoServicio.buscarPrecioCodPedido(codigo), HttpStatus.OK);
    }

    @GetMapping("/unicoprodcpedido/{cod}")
    public ResponseEntity<pedidos_productos> pedidosproductosPorCD(@PathVariable Long cod) {

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
    public ResponseEntity<Void> eliminarPedidosProductos(@PathVariable Long cod){
        if(pedidoProductoServicio.pedidosproductosPorCD(cod)!=null){
            pedidoProductoServicio.eliminarPedidosProductos(cod);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    }





