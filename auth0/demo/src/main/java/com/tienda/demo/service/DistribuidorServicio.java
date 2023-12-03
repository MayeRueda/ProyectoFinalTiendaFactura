package com.tienda.demo.service;
import com.tienda.demo.Entidad.distribuidores;
import com.tienda.demo.repositorio.DistribuidorCrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistribuidorServicio {
    private DistribuidorCrudRepository distribuidorCrudRepository;

    public DistribuidorServicio(DistribuidorCrudRepository distribuidorCrudRepository) {
        this.distribuidorCrudRepository = distribuidorCrudRepository;
    }

    public distribuidores insertardistribuidores(distribuidores Distribuidores) {
        return distribuidorCrudRepository.save(Distribuidores);
    }

    public distribuidores distribuidoresPorCC(Long Nit_Distribuidor) {
        if (distribuidorCrudRepository.findById(Nit_Distribuidor).isPresent()) {
            return distribuidorCrudRepository.findById(Nit_Distribuidor).get();
        } else {
            return null;
        }
    }

    public List<distribuidores> listaDistribuidores() {
        return (List<distribuidores>) distribuidorCrudRepository.findAll();
    }
        public List<distribuidores> nombreDistribuidores (String nomDistribuidor){
            return distribuidorCrudRepository.findByNomDistribuidor(nomDistribuidor);
        }
    public void eliminarnitDistribuidor (Long nit_Distribuidor){
        distribuidorCrudRepository.deleteById(nit_Distribuidor);

    }
        public void actualizarDistribuidores (distribuidores Distribuidores){
            distribuidorCrudRepository.actualizarDistribuidores(Distribuidores.getNomDistribuidor(), Distribuidores.getNitDistribuidor());
        }


    }
