package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.models.BairroModel;
import com.example.demo.services.BairroService;

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
@RequestMapping(value = "/bairros")
public class BairroController {

    @Autowired
    private BairroService service;

    //Buscar todos os bairros
    @GetMapping
    public ResponseEntity<List<BairroModel>> getAll(){

        List<BairroModel> listModel = service.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(listModel);
    }  

    //Paginação
    @GetMapping("/bairros-page")
    public Page<BairroModel> getPosts(Pageable pageable) {
        return service.getAll(pageable);
    }
    
    //Buscar bairro pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<BairroModel> get(@PathVariable int id){   

        BairroModel usuarios = service.find(id);

        return ResponseEntity.status(HttpStatus.OK).body(usuarios); 
    }

    //Editar bairro pelo ID
    @PutMapping("/{id}")
    public ResponseEntity<BairroModel> update(@RequestBody BairroModel model, @PathVariable int id) {

        model.setId(id);
        model = service.update(model);
        return ResponseEntity.noContent().build();
    }
     
    //Apagar bairro pelo ID
     @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        service.delete(id);     
        return ResponseEntity.noContent().build();  

    }

    //Criar bairro
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody BairroModel bairro) {

        BairroModel savedBairro = service.insert(bairro);

        //Converter Model para DTO
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedBairro.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

}