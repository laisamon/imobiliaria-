package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.models.TipoImovelModel;
import com.example.demo.services.TipoImovelService;

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
@RequestMapping(value = "/tipos_imovel")
public class TipoImovelController {

    @Autowired
    private TipoImovelService service;

    //Buscar todos os tipos de imóvel
    @GetMapping
    public ResponseEntity<List<TipoImovelModel>> getAll(){

        List<TipoImovelModel> listModel = service.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(listModel);
    }  

    //Paginação
    @GetMapping("/tipos_imovel-page")
    public Page<TipoImovelModel> getPosts(Pageable pageable) {
        return service.getAll(pageable);
    }
    
    //Buscar tipo de imóvel pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<TipoImovelModel> get(@PathVariable int id){   

        TipoImovelModel usuarios = service.find(id);

        return ResponseEntity.status(HttpStatus.OK).body(usuarios); 
    }

    //Editar tipo de imóvel pelo ID
    @PutMapping("/{id}")
    public ResponseEntity<TipoImovelModel> update(@RequestBody TipoImovelModel model, @PathVariable int id) {

        model.setId(id);
        model = service.update(model);
        return ResponseEntity.noContent().build();
    }
     
    //Apagar tipo de imóvel pelo ID
     @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        service.delete(id);     
        return ResponseEntity.noContent().build();  

    }

    //Criar tipo de imóvel
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody TipoImovelModel bairro) {

        TipoImovelModel savedBairro = service.insert(bairro);

        //Converter Model para DTO
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedBairro.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

}