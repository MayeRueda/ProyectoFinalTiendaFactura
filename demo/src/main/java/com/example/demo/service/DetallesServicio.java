package com.example.demo.service;
import com.example.demo.Entidad.comprar;
import com.example.demo.Entidad.detalles;
import com.example.demo.Entidad.pedidos_productos;
import com.example.demo.Entidad.productos;
import com.example.demo.repositorio.ComprarCrudRepository;
import com.example.demo.repositorio.DetallesCrudRepository;
import com.example.demo.repositorio.PedidoProductoCrudRepository;
import com.example.demo.repositorio.ProductoCrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DetallesServicio {
    private DetallesCrudRepository detallesCrudRepository;
    private PedidoProductoCrudRepository pedidoProductoCrudRepository;

    public DetallesServicio(DetallesCrudRepository detallesCrudRepository, PedidoProductoCrudRepository pedidoProductoCrudRepository) {
        this.detallesCrudRepository = detallesCrudRepository;
        this.pedidoProductoCrudRepository = pedidoProductoCrudRepository;

    }



    public detalles detallesPorCD(Integer cod_Identificacion) {
        if (detallesCrudRepository.findById(cod_Identificacion).isPresent()) {
            return detallesCrudRepository.findById(cod_Identificacion).get();
        } else {
            return null;
        }
    }
    public String agregarDetalles(detalles detalle ){
        pedidos_productos pdr= pedidoProductoCrudRepository.findById(detalle.getPedidos_productos().getCod()).get();
        if(pedidoProductoCrudRepository.findById(detalle.getPedidos_productos().getCod()).isPresent()){
            detalle.setPedidos_productos(pdr);
            detallesCrudRepository.save(detalle);
            return "Detalle Registrado";
        }
        else return "El PedidoProducto no existe.";
    }
    public detalles actualizarDetalles(detalles Detalles){
        Detalles.setPedidos_productos(pedidoProductoCrudRepository.findById(Detalles.getPedidos_productos().getCod()).get());
        return detallesCrudRepository.save(Detalles);

    }

    public boolean buscardetalleporcodPedidoProducto(Integer cod  ) {
        if (detallesCrudRepository.BuscardetalleporcodPedidoProducto(cod).size()>0) {
            return true;
        } else {
            return false;
        }
    }
    public void eliminardetalle (Integer cod_Identificacion){
        detallesCrudRepository.deleteById(cod_Identificacion);

    }
    public Integer precioDetalle(Integer cod_identificacion){
        return detallesCrudRepository.precioDetalles(cod_identificacion);
    }
    public List<detalles> listardetalles() {
        return (List<detalles>) detallesCrudRepository.findAll();
    }
    public List<detalles> DetallesPorPrecio(int precio){
        return detallesCrudRepository.findByPrecio(precio);
    }

    }
