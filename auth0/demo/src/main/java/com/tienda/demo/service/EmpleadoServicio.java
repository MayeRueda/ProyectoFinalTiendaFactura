package com.tienda.demo.service;
import com.tienda.demo.Entidad.empleados;
import com.tienda.demo.repositorio.EmpleadoCrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServicio {
    private EmpleadoCrudRepository empleadoCrudRepository;

    public EmpleadoServicio(EmpleadoCrudRepository empleadoCrudRepository) {
        this.empleadoCrudRepository = empleadoCrudRepository;
    }

    public empleados insertarempleados(empleados Empleados) {
        return empleadoCrudRepository.save(Empleados);
    }
    public List<Object[]> empleadosConComprar(){
        return empleadoCrudRepository.empleadosConComprar();
    }

    public empleados empleadosPorCC(Long cc_Empleado) {
        if (empleadoCrudRepository.findById(cc_Empleado).isPresent()) {
            return empleadoCrudRepository.findById(cc_Empleado).get();
        } else {
            return null;
        }
    }
    public boolean buscarcodcompraEmpleados(Long cod_Compra  ) {
        if (empleadoCrudRepository.BuscarDetallePorEmpleaods(cod_Compra).size()>0) {
            return true;
        } else {
            return false;
        }
    }
    public List<empleados> listaempleados() {
        return (List<empleados>) empleadoCrudRepository.findAll();
    }

        public void eliminarEmpleados (Long id){
            empleadoCrudRepository.deleteById(id);
        }
        public void actualizarEmpleados (empleados Empleados){
            empleadoCrudRepository.actualizarEmpleados(Empleados.getNomEmpleado(), Empleados.getSalario_Empleado(), Empleados.getHorario(), Empleados.getCargo(), Empleados.getCc_Empleado());
        }


    }
