package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.demo.models.ImagemModel;
import com.example.demo.services.ImagemService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping(value = "/imagens")
public class ImagemController {

    @Autowired
    private ImagemService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> salvar(
        @RequestPart("arquivo") MultipartFile arquivo, 
        @RequestPart("dados") String dadoString
    ) {
        try {
            // Converter o JSON recebido para ImagemModel
            ObjectMapper mapper = new ObjectMapper();
            ImagemModel imagemDTO = mapper.readValue(dadoString, ImagemModel.class);

            // Definir e criar a pasta de destino
            String caminhoPasta = "C:\\Users\\laisa\\OneDrive\\Imagens\\imoveis\\";
            File pasta = new File(caminhoPasta);
            if (!pasta.exists()) {
                pasta.mkdirs();
            }

            // Salvar o arquivo fisicamente
            String nomeArquivo = System.currentTimeMillis() + "_" + arquivo.getOriginalFilename();
            Path caminhoArquivo = Paths.get(caminhoPasta, nomeArquivo);
            Files.copy(arquivo.getInputStream(), caminhoArquivo);

            // Preencher o modelo e salvar no banco
            imagemDTO.setNome(nomeArquivo);
            imagemDTO.setCaminho(caminhoArquivo.toString());
            service.insert(imagemDTO);

            // Retornar sucesso
            return ResponseEntity.ok("Imagem salva com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao salvar imagem: " + e.getMessage());
        }
    }
}
