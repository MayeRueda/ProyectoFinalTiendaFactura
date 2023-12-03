package com.tienda.demo.service;
import com.tienda.demo.Entidad.distribuidores;
import com.tienda.demo.Entidad.pedidos;
import com.tienda.demo.repositorio.DistribuidorCrudRepository;
import com.tienda.demo.repositorio.PedidoCrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class PedidoServicio {
    private PedidoCrudRepository pedidoCrudRepository;
    private DistribuidorCrudRepository distribuidorCrudRepository;

    public PedidoServicio(PedidoCrudRepository pedidoCrudRepository, DistribuidorCrudRepository distribuidorCrudRepository) {
        this.pedidoCrudRepository = pedidoCrudRepository;
        this.distribuidorCrudRepository = distribuidorCrudRepository;
    }

    public pedidos pedidosPorCD(Long cod_Pedido) {
        if (pedidoCrudRepository.findById(cod_Pedido).isPresent()) {
            return pedidoCrudRepository.findById(cod_Pedido).get();
        } else {
            return null;
        }
    }

    public String agregarPedidos(pedidos Pedidos ){
        distribuidores dst= distribuidorCrudRepository.findById(Pedidos.getDistribuidoresA().getNitDistribuidor()).get();
        if(distribuidorCrudRepository.findById(Pedidos.getDistribuidoresA().getNitDistribuidor()).isPresent() ){
            Pedidos.setDistribuidoresA(dst);
            Pedidos.setFechapedido(LocalDate.now());
            Pedidos.setHorapedido(LocalTime.now());
            pedidoCrudRepository.save(Pedidos);
            return "Pedido Registrado";
        }
        else return "El distribuidor  no existen.";
    }
    public pedidos actualizarPedidos(pedidos Pedidos){
        Pedidos.setDistribuidoresA(distribuidorCrudRepository.findById(Pedidos.getDistribuidoresA().getNitDistribuidor()).get());
        Pedidos.setFechapedido(LocalDate.now());
        Pedidos.setHorapedido(LocalTime.now());

        return pedidoCrudRepository.save(Pedidos);

    }
    public boolean buscarpedidopornitdistribuidor(Long nit_Distribuidor  ) {
        if (pedidoCrudRepository.BuscarPedidoPornitDistribuidor(nit_Distribuidor).size()>0) {
            return true;
        } else {
            return false;
        }
    }
    public void eliminarPedido (Long cod_Pedido){
        pedidoCrudRepository.deleteById(cod_Pedido);

    }
    public List<pedidos> listarpedidos() {
        return (List<pedidos>) pedidoCrudRepository.findAll();
    }
    public List<pedidos> PedidoPorCosto(int costoNeto){
        return pedidoCrudRepository.findByCostoNeto(costoNeto);
    }

    }
