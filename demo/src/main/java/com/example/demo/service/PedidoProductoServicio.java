package com.example.demo.service;
import com.example.demo.Entidad.*;
import com.example.demo.repositorio.PedidoCrudRepository;
import com.example.demo.repositorio.PedidoProductoCrudRepository;
import com.example.demo.repositorio.ProductoCrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoProductoServicio {
    private PedidoProductoCrudRepository pedidoProductoCrudRepository;
    private PedidoCrudRepository pedidoCrudRepository;
    private ProductoCrudRepository productoCrudRepository;

    public PedidoProductoServicio(PedidoProductoCrudRepository pedidoProductoCrudRepository, PedidoCrudRepository pedidoCrudRepository, ProductoCrudRepository productoCrudRepository) {
        this.pedidoProductoCrudRepository = pedidoProductoCrudRepository;
        this.pedidoCrudRepository = pedidoCrudRepository;
        this.productoCrudRepository = productoCrudRepository;
    }

    public pedidos_productos pedidosproductosPorCD(Integer cod) {
        if (pedidoProductoCrudRepository.findById(cod).isPresent()) {
            return pedidoProductoCrudRepository.findById(cod).get();
        } else {
            return null;
        }
    }

    public List<pedidos_productos> listarpedidosproductos() {
        return (List<pedidos_productos>) pedidoProductoCrudRepository.findAll();
    }
    public String agregarPedidosProductos(pedidos_productos pedidoProducto ){
        pedidos ped= pedidoCrudRepository.findById(pedidoProducto.getPedidos().getCod_Pedido() ).get();
        productos prds= productoCrudRepository.findById(pedidoProducto.getProductos().getCod_Producto()).get();
        if(productoCrudRepository.findById(pedidoProducto.getProductos().getCod_Producto()).isPresent() && pedidoCrudRepository.findById(pedidoProducto.getPedidos().getCod_Pedido()).isPresent()){
            pedidoProducto.setProductos(prds);
            pedidoProducto.setPedidos(ped);
            pedidoProductoCrudRepository.save(pedidoProducto);
            return "Pedidos_Productos Registrado";
        }
        else return "El Producto y/o Pedido no existen.";
    }
    public boolean buscarpedidoproductPorcod_Pedido(Integer cod_Pedido  ) {
        if (pedidoProductoCrudRepository.BuscarPedidoproductPorcod_Pedido(cod_Pedido).size()>0) {
            return true;
        } else {
            return false;
        }
    }
    public int buscarPrecioCodPedido(int codigo){
        return pedidoProductoCrudRepository.buscarPrecioCodPedido(codigo);
    }
    public pedidos_productos actualizarPedidosProductos(pedidos_productos PedidosProductos){
        PedidosProductos.setPedidos(pedidoCrudRepository.findById(PedidosProductos.getPedidos().getCod_Pedido()).get());
        PedidosProductos.setProductos(productoCrudRepository.findById(PedidosProductos.getProductos().getCod_Producto()).get());

        return pedidoProductoCrudRepository.save(PedidosProductos);

    }
    public void eliminarPedidosProductos (Integer id){
        pedidoProductoCrudRepository.deleteById(id);
    }
    public boolean buscarpedidoproductoporcod_Producto(Integer cod_Producto  ) {
        if (pedidoProductoCrudRepository.Buscarpedidoproductoporcod_Producto(cod_Producto).size()>0) {
            return true;
        } else {
            return false;
        }
    }
    }
