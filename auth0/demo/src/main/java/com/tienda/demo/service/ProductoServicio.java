package com.tienda.demo.service;
import com.tienda.demo.Entidad.pedidos;
import com.tienda.demo.Entidad.productos;
import com.tienda.demo.repositorio.PedidoCrudRepository;
import com.tienda.demo.repositorio.ProductoCrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicio {
    private ProductoCrudRepository productoCrudRepository;
    private PedidoCrudRepository pedidoCrudRepository;

    public ProductoServicio(ProductoCrudRepository productoCrudRepository, PedidoCrudRepository pedidoCrudRepository) {
        this.productoCrudRepository = productoCrudRepository;
        this.pedidoCrudRepository = pedidoCrudRepository;
    }

    public productos productosPorCD(Long cod_Producto) {
        if (productoCrudRepository.findById(cod_Producto).isPresent()) {
            return productoCrudRepository.findById(cod_Producto).get();
        } else {
            return null;
        }
    }
    public List<Object[]> productosEnPedidos(){
        return  productoCrudRepository.ProductosConPedidos();
    }

    public List<productos> listarproductos() {
        return (List<productos>) productoCrudRepository.findAll();
    }
    public List<productos> ProductoPorNombre(String nomProducto){
        return productoCrudRepository.findByNomProducto(nomProducto);
    }
    public String agregarProductos(productos Productos ){
        pedidos ped= pedidoCrudRepository.findById(Productos.getPedidos().getCod_Pedido() ).get();
        if(pedidoCrudRepository.findById(Productos.getPedidos().getCod_Pedido()).isPresent()){
            Productos.setPedidos(ped);
            productoCrudRepository.save(Productos);
            return "Producto Registrado";
        }
        else return "El Pedido no existen.";
    }
    public productos actualizarProductos(productos Productos){
        Productos.setPedidos(pedidoCrudRepository.findById(Productos.getPedidos().getCod_Pedido()).get());

        return productoCrudRepository.save(Productos);

    }
    public void eliminarProducto (Long cod_Producto){
        productoCrudRepository.deleteById(cod_Producto);

    }
    public boolean buscarproductoPorcod_Pedido(Long cod_Pedido  ) {
        if (productoCrudRepository.BuscarProductoPorcod_Pedido(cod_Pedido).size()>0) {
            return true;
        } else {
            return false;
        }
    }

    }
