package com.tienda.demo.controller;

import com.tienda.demo.Entidad.clientes;
import com.tienda.demo.service.ClienteServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin("*")
@RestController
@RequestMapping("/cliente")
public class ClienteControlador {
    private ClienteServicio clienteServicio;

    public ClienteControlador(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    @PostMapping("/insertar")
    public ResponseEntity<Void>insertarclientes(@RequestBody clientes Clientes){
        if (clienteServicio.clientesPorCC(Clientes.getCc_clientes())!=null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            clienteServicio.insertarclientes(Clientes);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
    @GetMapping("/listar")
    public ResponseEntity<List<clientes>>listarClientes(){
        return new ResponseEntity<>(clienteServicio.listaClientes(), HttpStatus.OK);

    }
    @GetMapping("/unico/{cc_clientes}")
    public ResponseEntity<clientes>clientesPorCC(@PathVariable Long cc_clientes){
        if(clienteServicio.clientesPorCC(cc_clientes)!=null){
            return new ResponseEntity<>(clienteServicio.clientesPorCC(cc_clientes),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{cc_clientes}")
    public ResponseEntity<Void> eliminarClientes(@PathVariable Long cc_clientes){
        if(clienteServicio.clientesPorCC(cc_clientes)!=null){
            clienteServicio.eliminarClientes(cc_clientes);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/actualizarSave")
    public ResponseEntity<Void> actualizarClientesSave(@RequestBody clientes Clientes){
        if(clienteServicio.clientesPorCC(Clientes.getCc_clientes())!=null){
            clienteServicio.insertarclientes(Clientes);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/clientesCompra")
    public ResponseEntity<List<Object[]>> clientesPorCompra(){
        return new ResponseEntity<>(clienteServicio.clientesPorCompra(), HttpStatus.OK);
    }
    @GetMapping("/detalleClientes")
    public List<Map<String, Object>> datosClientes(){
        List<Object[]> lista=clienteServicio.datosClientes();
        List<Map<String, Object>> json=new ArrayList<Map<String, Object>>();
        for(Object[] objects: lista){
            Map<String, Object> datos= new HashMap<>();
            datos.put("cc_clientes",objects[0]);
            datos.put("nomCliente",objects[1]);
            json.add(datos);
        }

        for(Map<String, Object> j : json){
            System.out.println(j);
        }
        return json;

    }
}





