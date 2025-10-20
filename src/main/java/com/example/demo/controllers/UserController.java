package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.dtos.UserDTO;
import com.example.demo.models.UserModel;
import com.example.demo.services.UserService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    //Buscar todos os usuários
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll(){

        List<UserModel> listModel = service.getAll();

        List<UserDTO> listDto = listModel.stream().map(usuario -> new UserDTO(usuario)).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(listDto);
    }  
    
    //Buscar usuário pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable int id){   

        UserModel usuarios = service.find(id);
        UserDTO dto = new UserDTO(usuarios);

        return ResponseEntity.status(HttpStatus.OK).body(dto); 
    }

    //Editar usuário pelo ID
    @PutMapping("/{id}")
    public ResponseEntity<UserModel> update(@RequestBody UserModel model, @PathVariable int id) {

        model.setId(id);
        model = service.update(model);
        return ResponseEntity.noContent().build();
    }
     
    //Apagar usuário pelo ID
     @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        service.delete(id);     
        return ResponseEntity.noContent().build();  

    }

    //Criar usuário
    @PostMapping()
    public ResponseEntity<Void> createUser(@RequestBody UserDTO dto) {

        UserModel savedUser = service.insert(dto);

        //Converter Model para DTO
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

}