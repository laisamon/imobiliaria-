package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.UserDTO;
import com.example.demo.models.UserModel;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository repositorio;

    //HTTP -> Controller -> Service getAll() -> Repository -> Banco de Dados
    public List<UserModel> getAll(){
        List<UserModel> lista = repositorio.findAll();
        return lista;
    }

    //Buscar objeto pelo ID
    public UserModel find(Integer id){
        Optional<UserModel> model = repositorio.findById(id);
        return model.orElse(null);
    }
    
    //Inserir objeto no banco
    public UserModel insert(UserModel user){
        return repositorio.save(user);
    }   

    public UserModel insert(UserDTO dto){
        UserModel model = new UserModel();
        model.setNome(dto.getNome());
        model.setEmail(dto.getEmail());
        model.setTipo(dto.getTipo());
        //Senha criptografada -> Futuamente
        return repositorio.save(model);
    }

    //Atualizar objeto no banco
    public UserModel update(UserModel user){
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