package com.example.demo.dtos;
import com.example.demo.models.UserModel;

public class UserDTO {
    private Integer id;
    private String nome;
    private String email;
    private String tipo;

    public UserDTO() {
    }

    public UserDTO(Integer id, String nome, String email, String tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tipo = tipo;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public UserDTO(UserModel userModel) {
        this.id = userModel.getId();
        this.nome = userModel.getNome();
        this.email = userModel.getEmail();
        this.tipo = userModel.getTipo();
    }
    
}
