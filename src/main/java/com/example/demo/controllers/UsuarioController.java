package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.dtos.UsuarioDTO;
import com.example.demo.models.UsuarioModel;
import com.example.demo.services.UsuarioService;

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
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    //Buscar todos os usuários
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAll(){

        List<UsuarioModel> listModel = service.getAll();

        List<UsuarioDTO> listDto = listModel.stream().map(usuario -> new UsuarioDTO(usuario)).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(listDto);
    }  
    
    //Buscar usuário pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> get(@PathVariable int id){   

        UsuarioModel usuarios = service.find(id);
        UsuarioDTO dto = new UsuarioDTO(usuarios);

        return ResponseEntity.status(HttpStatus.OK).body(dto); 
    }

    //Editar usuário pelo ID
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioModel> update(@RequestBody UsuarioModel model, @PathVariable int id) {

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
    public ResponseEntity<Void> createUser(@RequestBody UsuarioDTO dto) {

        UsuarioModel savedUser = service.insert(dto);

        //Converter Model para DTO
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

}