package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.models.ImovelModel;
import com.example.demo.repositories.ImovelRepository;

@Service
public class ImovelService {

    @Autowired
    ImovelRepository repositorio;

    //HTTP -> Controller -> Service getAll() -> Repository -> Banco de Dados
    public List<ImovelModel> getAll(){
        List<ImovelModel> lista = repositorio.findAll();
        return lista;
    }

    //Paginação
    public Page<ImovelModel> getAll(Pageable pageable) {
        Page<ImovelModel> list = repositorio.findAll(pageable);
        return list;
    }

    //Buscar objeto pelo ID
    public ImovelModel find(Integer id){
        Optional<ImovelModel> model = repositorio.findById(id);
        return model.orElse(null);
    }
    
    //Inserir objeto no banco
    public ImovelModel insert(ImovelModel objeto){
        return repositorio.save(objeto);
    }   

    //Atualizar objeto no banco
    public ImovelModel update(ImovelModel objeto){
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