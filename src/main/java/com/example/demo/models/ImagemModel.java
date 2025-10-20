package com.example.demo.models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "imagens")
public class ImagemModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String caminho;
    private Boolean capa;
    private Integer ordem;
    
    //Relacionamento com Imóvel
    @ManyToOne
    @JoinColumn(name = "imovel_id")
    private ImovelModel imovel;

    public ImagemModel() {
    }

    public ImagemModel(Integer id, String nome, String caminho, Boolean capa, Integer ordem, ImovelModel imovel) {
        this.id = id;
        this.nome = nome;
        this.caminho = caminho;
        this.capa = capa;
        this.ordem = ordem;
        this.imovel = imovel;
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

    public ImovelModel getImovel() {
        return imovel;
    }
    public void setImovel(ImovelModel imovel) {
        this.imovel = imovel;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ImagemModel other = (ImagemModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }    
}
