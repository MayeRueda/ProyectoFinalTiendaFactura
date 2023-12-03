package com.example.demo.service;
import com.example.demo.Entidad.clientes;
import com.example.demo.repositorio.ClienteCrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClienteServicio {
    private ClienteCrudRepository clienteCrudRepository;

    public ClienteServicio(ClienteCrudRepository clienteCrudRepository) {
        this.clienteCrudRepository = clienteCrudRepository;
    }

    public clientes insertarclientes(clientes Clientes) {
        return clienteCrudRepository.save(Clientes);
    }

    public clientes clientesPorCC(Integer cc_clientes) {
        if (clienteCrudRepository.findById(cc_clientes).isPresent()) {
            return clienteCrudRepository.findById(cc_clientes).get();
        } else {
            return null;
        }
    }
    public boolean buscarcodcompraclientes(Integer cod_Compra  ) {
        if (clienteCrudRepository.BuscarDetalleEnClientes(cod_Compra).size()>0) {
            return true;
        } else {
            return false;
        }
    }
    public List<clientes> listaClientes() {
        return (List<clientes>) clienteCrudRepository.findAll();
    }
    public List<Object[]> datosClientes(){
        return clienteCrudRepository.findDatosClientes();
    }
    public void eliminarClientes (Integer id){
        clienteCrudRepository.deleteById(id);
    }
    public void actualizarClientes (clientes Clientes){
        clienteCrudRepository.actualizarClientes(Clientes.getNomCliente(), Clientes.getCc_clientes());
    }
    public List<Object[]>clientesPorCompra(){
       return clienteCrudRepository.clientesPorCompra();
    }

    }
