package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.models.BairroModel;
import com.example.demo.repositories.BairroRepository;

@Service
public class BairroService {

    @Autowired
    BairroRepository repositorio;

    //HTTP -> Controller -> Service getAll() -> Repository -> Banco de Dados
    public List<BairroModel> getAll(){
        List<BairroModel> lista = repositorio.findAll();
        return lista;
    }

    //Paginação
    public Page<BairroModel> getAll(Pageable pageable) {
        Page<BairroModel> list = repositorio.findAll(pageable);
        return list;
    }

    //Buscar objeto pelo ID
    public BairroModel find(Integer id){
        Optional<BairroModel> model = repositorio.findById(id);
        return model.orElse(null);
    }
    
    //Inserir objeto no banco
    public BairroModel insert(BairroModel objeto){
        return repositorio.save(objeto);
    }   

    //Atualizar objeto no banco
    public BairroModel update(BairroModel objeto){
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