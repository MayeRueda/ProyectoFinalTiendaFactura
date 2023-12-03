package com.example.demo.controller;

import com.tienda.demo.Entidad.productos;
import com.tienda.demo.service.DetallesServicio;
import com.tienda.demo.service.PedidoProductoServicio;
import com.tienda.demo.service.ProductoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/producto")
public class ProductoControlador {
    private ProductoServicio productoServicio;

    private DetallesServicio detallesServicio;
    private PedidoProductoServicio pedidoProductoServicio;

    public ProductoControlador(ProductoServicio productoServicio, DetallesServicio detallesServicio, PedidoProductoServicio pedidoProductoServicio) {
        this.productoServicio = productoServicio;
        this.detallesServicio = detallesServicio;
        this.pedidoProductoServicio = pedidoProductoServicio;
    }

    @GetMapping("/listarprodc")
    public ResponseEntity<List<productos>> listarpedidos() {
        return new ResponseEntity<>(productoServicio.listarproductos(), HttpStatus.OK);

    }

    @GetMapping("/unicoprodc/{cod_Producto}")
    public ResponseEntity<productos> pedidosPorCD(@PathVariable Long cod_Producto) {
        if (productoServicio.productosPorCD(cod_Producto) != null) {
            return new ResponseEntity<>(productoServicio.productosPorCD(cod_Producto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/unicoprcts/{nomProducto}")
    public ResponseEntity<List<productos>> ProductoPorNombre(@PathVariable("nomProducto") String nomProducto) {
        return new ResponseEntity<>(productoServicio.ProductoPorNombre(nomProducto), HttpStatus.OK);
    }
    @GetMapping("/productosEnPedidos")
    public ResponseEntity<List<Object[]>> productosEnPedidos(){
        return new ResponseEntity<>(productoServicio.productosEnPedidos(), HttpStatus.OK);
    }

    @PostMapping("/agregarProductos")
    public String agregarProductos(@RequestBody productos Productos) {
        return productoServicio.agregarProductos(Productos);
    }

    @PutMapping("/actualizarProductos")

    public ResponseEntity<Void> actualizarProductos(@RequestBody productos Productos) {
        if (productoServicio.productosPorCD(Productos.getCod_Producto()) != null) {
            productoServicio.actualizarProductos(Productos);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping("/eliminarproductom/{cod_Producto}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long cod_Producto) {
        if (productoServicio.productosPorCD(cod_Producto) != null) {
            if (detallesServicio.buscardetalleporcodPedidoProducto(cod_Producto)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            if (pedidoProductoServicio.buscarpedidoproductoporcod_Producto(cod_Producto)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }else{
                productoServicio.eliminarProducto(cod_Producto);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}






