package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.models.ImovelModel;
import com.example.demo.services.ImovelService;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping(value = "/imoveis")
public class ImovelController {

    @Autowired
    private ImovelService service;

    //Buscar todos os imóveis
    @GetMapping
    public ResponseEntity<List<ImovelModel>> getAll(){

        List<ImovelModel> listModel = service.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(listModel);
    }  

    //Paginação
    @GetMapping("/imoveis-page")
    public Page<ImovelModel> getPosts(Pageable pageable) {
        return service.getAll(pageable);
    }
    
    //Buscar imóvel pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<ImovelModel> get(@PathVariable int id){   

        ImovelModel usuarios = service.find(id);

        return ResponseEntity.status(HttpStatus.OK).body(usuarios); 
    }

    //Editar imóvel pelo ID
    @PutMapping("/{id}")
    public ResponseEntity<ImovelModel> update(@RequestBody ImovelModel model, @PathVariable int id) {

        model.setId(id);
        model = service.update(model);
        return ResponseEntity.noContent().build();
    }
     
    //Apagar imóvel pelo ID
     @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        service.delete(id);     
        return ResponseEntity.noContent().build();  

    }

    //Criar imóvel
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody ImovelModel bairro) {

        ImovelModel savedBairro = service.insert(bairro);

        //Converter Model para DTO
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedBairro.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

}