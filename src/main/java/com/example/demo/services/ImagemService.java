package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.models.ImagemModel;
import com.example.demo.repositories.ImagemRepository;

@Service
public class ImagemService {

    @Autowired
    ImagemRepository repositorio;

    //HTTP -> Controller -> Service getAll() -> Repository -> Banco de Dados
    public List<ImagemModel> getAll(){
        List<ImagemModel> lista = repositorio.findAll();
        return lista;
    }

    //Paginação
    public Page<ImagemModel> getAll(Pageable pageable) {
        Page<ImagemModel> list = repositorio.findAll(pageable);
        return list;
    }

    //Buscar objeto pelo ID
    public ImagemModel find(Integer id){
        Optional<ImagemModel> model = repositorio.findById(id);
        return model.orElse(null);
    }
    
    //Inserir objeto no banco
    public ImagemModel insert(ImagemModel imagem){
        return repositorio.save(imagem);
    }   

    //Atualizar objeto no banco
    public ImagemModel update(ImagemModel objeto){
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