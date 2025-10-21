package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.UsuarioDTO;
import com.example.demo.models.UsuarioModel;
import com.example.demo.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repositorio;

    //HTTP -> Controller -> Service getAll() -> Repository -> Banco de Dados
    public List<UsuarioModel> getAll(){
        List<UsuarioModel> lista = repositorio.findAll();
        return lista;
    }

    //Buscar objeto pelo ID
    public UsuarioModel find(Integer id){
        Optional<UsuarioModel> model = repositorio.findById(id);
        return model.orElse(null);
    }
    
    //Inserir objeto no banco
    public UsuarioModel insert(UsuarioModel user){
        return repositorio.save(user);
    }   

    public UsuarioModel insert(UsuarioDTO dto){
        UsuarioModel model = new UsuarioModel();
        model.setNome(dto.getNome());
        model.setEmail(dto.getEmail());
        model.setTipo(dto.getTipo());
        //Senha criptografada -> Futuamente
        return repositorio.save(model);
    }

    //Atualizar objeto no banco
    public UsuarioModel update(UsuarioModel user){
        try {
            if(find(user.getId()) != null){
                return repositorio.save(user);
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