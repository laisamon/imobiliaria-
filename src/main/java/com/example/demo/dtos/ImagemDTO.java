package com.example.demo.dtos;

public class ImagemDTO {
    private Integer id;
    private String nome;
    private String caminho;
    private Boolean capa;
    private Integer ordem;


    public ImagemDTO() {
    }

    public ImagemDTO(Integer id, String nome, String caminho, Boolean capa, Integer ordem) {
        this.id = id;
        this.nome = nome;
        this.caminho = caminho;
        this.capa = capa;
        this.ordem = ordem;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCaminho() {
        return caminho;
    }
    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public Boolean getCapa() {
        return capa;
    }
    public void setCapa(Boolean capa) {
        this.capa = capa;
    }

    public Integer getOrdem() {
        return ordem;
    }
    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }       
}
