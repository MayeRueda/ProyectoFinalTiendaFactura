package com.tienda.demo.service;

import com.tienda.demo.Entidad.clientes;
import com.tienda.demo.Entidad.comprar;
import com.tienda.demo.Entidad.detalles;
import com.tienda.demo.Entidad.empleados;
import com.tienda.demo.repositorio.ClienteCrudRepository;
import com.tienda.demo.repositorio.ComprarCrudRepository;
import com.tienda.demo.repositorio.DetallesCrudRepository;
import com.tienda.demo.repositorio.EmpleadoCrudRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ComprarServicio {
    private ComprarCrudRepository comprarCrudRepository;
    private ClienteCrudRepository clienteCrudRepository;
    private EmpleadoCrudRepository empleadoCrudRepository;
    private DetallesCrudRepository detallesCrudRepository;


    public ComprarServicio(ComprarCrudRepository comprarCrudRepository, ClienteCrudRepository clienteCrudRepository, EmpleadoCrudRepository empleadoCrudRepository, DetallesCrudRepository detallesCrudRepository) {
        this.comprarCrudRepository = comprarCrudRepository;
        this.clienteCrudRepository = clienteCrudRepository;
        this.empleadoCrudRepository = empleadoCrudRepository;
        this.detallesCrudRepository = detallesCrudRepository;
    }

    public comprar comprarPorCD(Long cod_Compra) {
        if (comprarCrudRepository.findById(cod_Compra).isPresent()) {
            return comprarCrudRepository.findById(cod_Compra).get();
        } else {
            return null;
        }
    }

    public comprar insertarcompras(comprar Comprar) {
        return comprarCrudRepository.save(Comprar);
    }

    public List<comprar> listarcomprar() {
        return (List<comprar>) comprarCrudRepository.findAll();
    }


    public List<comprar> ComprarPorMetodopago(String metodoPago){
        return comprarCrudRepository.findByMetodoPago(metodoPago);
    }
    public String agregarCompras(comprar Compras ){
        clientes cl= clienteCrudRepository.findById(Compras.getClientes().getCc_clientes() ).get();
        empleados em= empleadoCrudRepository.findById(Compras.getEmpleados().getCc_Empleado()).get();
        detalles dtl= detallesCrudRepository.findById(Compras.getDetalles().getCodigodetalle()).get();
        if(empleadoCrudRepository.findById(Compras.getEmpleados().getCc_Empleado()).isPresent() && clienteCrudRepository.findById(Compras.getClientes().getCc_clientes()).isPresent() && detallesCrudRepository.findById(Compras.getDetalles().getCodigodetalle()).isPresent()){
            Compras.setEmpleados(em);
            Compras.setClientes(cl);
            Compras.setDetalles(dtl);
            Compras.setFecha(LocalDate.now());
            Compras.setHora(LocalTime.now());
            comprarCrudRepository.save(Compras);
            return "Compra Registrada";
        }
        else return "El cliente y/o empleado no existen.";
    }

    public comprar actualizarCompra(comprar Comprar){
        Comprar.setClientes(clienteCrudRepository.findById(Comprar.getClientes().getCc_clientes()).get());
        Comprar.setEmpleados(empleadoCrudRepository.findById(Comprar.getEmpleados().getCc_Empleado()).get());
        Comprar.setDetalles(detallesCrudRepository.findById(Comprar.getDetalles().getCodigodetalle()).get());
        Comprar.setFecha(LocalDate.now());
        Comprar.setHora(LocalTime.now());
        return comprarCrudRepository.save(Comprar);

    }
    public void eliminarcomprar (Long cod_Compra){
        comprarCrudRepository.deleteById(cod_Compra);

    }

    public boolean buscarpedidoporcc_empleado(Long cc_Empleado  ) {
        if (comprarCrudRepository.BuscarPedidoPorcc_Empleado(cc_Empleado).size()>0) {
            return true;
        } else {
            return false;
        }
    }
}
