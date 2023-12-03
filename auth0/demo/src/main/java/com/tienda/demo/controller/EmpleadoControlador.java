package com.tienda.demo.controller;
import com.tienda.demo.Entidad.empleados;
import com.tienda.demo.service.ComprarServicio;
import com.tienda.demo.service.EmpleadoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/empleado")
public class EmpleadoControlador {
    private EmpleadoServicio empleadoServicio;
    private ComprarServicio comprarServicio;

    public EmpleadoControlador(EmpleadoServicio empleadoServicio, ComprarServicio comprarServicio) {
        this.empleadoServicio = empleadoServicio;
        this.comprarServicio = comprarServicio;
    }

    @PostMapping("/insertarem")
    public ResponseEntity<Void>insertarempleados(@RequestBody empleados Empleados){
        if (empleadoServicio.empleadosPorCC(Empleados.getCc_Empleado())!=null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            empleadoServicio.insertarempleados(Empleados);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
    @GetMapping("/listarem")
    public ResponseEntity<List<empleados>>listarEmpleados(){
        return new ResponseEntity<>(empleadoServicio.listaempleados(), HttpStatus.OK);

    }
    @GetMapping("/listarEmpleadosConComprar")
    public ResponseEntity<List<Object[]>>listarEmpleadosConComprar(){
        return new ResponseEntity<>(empleadoServicio.empleadosConComprar(), HttpStatus.OK);
    }
    @GetMapping("/unicoem/{cc_Empleado}")
    public ResponseEntity<empleados>empleadosPorCC(@PathVariable Long cc_Empleado){
        if(empleadoServicio.empleadosPorCC(cc_Empleado)!=null){
            return new ResponseEntity<>(empleadoServicio.empleadosPorCC(cc_Empleado),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/eliminarem/{cc_Empleado}")
    public ResponseEntity<Void> eliminarnitDistribuidor(@PathVariable Long cc_Empleado) {
        if (empleadoServicio.empleadosPorCC(cc_Empleado) != null) {
            if (comprarServicio.buscarpedidoporcc_empleado(cc_Empleado)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            else{
                empleadoServicio.eliminarEmpleados(cc_Empleado);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }






    @PutMapping("/actualizarem/{cc_Empleado}")
    public ResponseEntity<Void> actualizarEmpleados(@RequestBody empleados Empleados){
        if(empleadoServicio.empleadosPorCC(Empleados.getCc_Empleado())!=null){
            empleadoServicio.actualizarEmpleados(Empleados);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/actualizaremSave")
    public ResponseEntity<Void> actualizarEmpleadosemSave(@RequestBody empleados Empleados){
        if(empleadoServicio.empleadosPorCC(Empleados.getCc_Empleado())!=null){
            empleadoServicio.insertarempleados(Empleados);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}





