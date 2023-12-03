package com.example.demo.service;
import com.example.demo.Entidad.*;
import com.example.demo.repositorio.DetallesCrudRepository;
import com.example.demo.repositorio.DevolucionCrudRepository;
import com.example.demo.repositorio.ProductoCrudRepository;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevolucionServicio {
    private DevolucionCrudRepository devolucionCrudRepository;
    private DetallesCrudRepository detallesCrudRepository;

    public DevolucionServicio(DevolucionCrudRepository devolucionCrudRepository, DetallesCrudRepository detallesCrudRepository) {
        this.devolucionCrudRepository = devolucionCrudRepository;
        this.detallesCrudRepository = detallesCrudRepository;
    }

    public devoluciones devolucionPorCD(Integer cod_Devolucion) {
        if (devolucionCrudRepository.findById(cod_Devolucion).isPresent()) {
            return devolucionCrudRepository.findById(cod_Devolucion).get();
        } else {
            return null;
        }
    }

    public List<devoluciones> listardevoluciones() {
        return (List<devoluciones>) devolucionCrudRepository.findAll();
    }

    public List<devoluciones> devolucionporUnidad(int unidades) {
        return devolucionCrudRepository.findByUnidades(unidades);
    }


    public String agregarDevolucion(devoluciones Devoluciones ){
        detalles det= detallesCrudRepository.findById(Devoluciones.getDetalles().getCod_Identificacion() ).get();
        if(detallesCrudRepository.findById(Devoluciones.getDetalles().getCod_Identificacion()).isPresent()){
            System.out.println("HOLA");
            Devoluciones.setDetalles(det);
            devolucionCrudRepository.save(Devoluciones);
            return "devolucion Registrada";
        }
        else return "El detalle  no existen.";
    }
    public devoluciones actualizarDevoluciones(devoluciones Devoluciones){
        Devoluciones.setDetalles(detallesCrudRepository.findById(Devoluciones.getDetalles().getCod_Identificacion()).get());
        return devolucionCrudRepository.save(Devoluciones);

    }
    public void eliminarDevolucion (Integer id){
        devolucionCrudRepository.deleteById(id);
    }
    public boolean buscarcodidentificaciondetalles(Integer cod_Identificacion  ) {
        if (devolucionCrudRepository.BuscarDetallePorcodIdentificacion(cod_Identificacion).size()>0) {
            return true;
        } else {
            return false;
        }
    }
    }
