package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.models.TipoImovelModel;
import com.example.demo.repositories.TipoImovelRepository;

@Service
public class TipoImovelService {

    @Autowired
    TipoImovelRepository repositorio;

    //HTTP -> Controller -> Service getAll() -> Repository -> Banco de Dados
    public List<TipoImovelModel> getAll(){
        List<TipoImovelModel> lista = repositorio.findAll();
        return lista;
    }

    //Paginação
    public Page<TipoImovelModel> getAll(Pageable pageable) {
        Page<TipoImovelModel> list = repositorio.findAll(pageable);
        return list;
    }

    //Buscar objeto pelo ID
    public TipoImovelModel find(Integer id){
        Optional<TipoImovelModel> model = repositorio.findById(id);
        return model.orElse(null);
    }
    
    //Inserir objeto no banco
    public TipoImovelModel insert(TipoImovelModel objeto){
        return repositorio.save(objeto);
    }   

    //Atualizar objeto no banco
    public TipoImovelModel update(TipoImovelModel objeto){
        try {
            if(find(objeto.getId()) != null){
                return repositorio.save(objeto);
            } 
            return null;

        } catch (Exception e) {
            return null;
        }   
    }

    //Deletar objeto no banco
    public void delete(Integer id){
        repositorio.deleteById(id);
    }
}