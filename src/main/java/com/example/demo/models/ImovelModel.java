package com.example.demo.models;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "imoveis")
public class ImovelModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @Column(columnDefinition = "TEXT")
    private String descricao;
    @Column(name  = "preco_venda", precision = 15, scale = 2)
    private BigDecimal precoVenda;
    @Column(name  = "preco_aluguel", precision = 15, scale = 2)
    private BigDecimal precoAluguel;
    private String finalidade;
    private String status;
    private Integer dormitorios;
    private Integer banheiros;
    private Integer garagem;  
    @Column(name  = "area_total", precision = 15, scale = 2)
    private BigDecimal areaTotal;
    @Column(name  = "area_construida", precision = 15, scale = 2)
    private BigDecimal areaConstruida;

    //Endereço - Melhoria Futura
    private String endereco;
    private String numero;
    private String complemento;
    @Column(columnDefinition = "TEXT")
    private String caracteristicas;
    private Boolean destaque;

    //Relacionamentos - Impedir Referência Cíclica
    /* 
        @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "tipo_imovel_id")
        private TipoImovelModel tipoImovel;

        @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "bairro_id")
        private BairroModel bairro;

        @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "usuario_id")   
        private UserModel usuario;
    */

    public ImovelModel() {
    }

    public ImovelModel(Integer id, String nome, String descricao, BigDecimal precoVenda, BigDecimal precoAluguel, String finalidade,
    String status, Integer dormitorios, Integer banheiros, Integer garagem, BigDecimal areaTotal,
    BigDecimal areaConstruida, String endereco, String numero, String complemento,
    String caracteristicas, Boolean destaque, TipoImovelModel tipoImovel, BairroModel bairro,
    UsuarioModel usuario) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.precoVenda = precoVenda;
        this.precoAluguel = precoAluguel;
        this.finalidade = finalidade;
        this.status = status;
        this.dormitorios = dormitorios;
        this.banheiros = banheiros;
        this.garagem = garagem;
        this.areaTotal = areaTotal;
        this.areaConstruida = areaConstruida;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.caracteristicas = caracteristicas;
        this.destaque = destaque;
        /*this.tipoImovel = tipoImovel;
        this.bairro = bairro;
        this.usuario = usuario;*/
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

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }
    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public BigDecimal getPrecoAluguel() {
        return precoAluguel;
    }
    public void setPrecoAluguel(BigDecimal precoAluguel) {
        this.precoAluguel = precoAluguel;
    }

    public String getFinalidade() {
        return finalidade;
    }
    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDormitorios() {
        return dormitorios;
    }
    public void setDormitorios(Integer dormitorios) {
        this.dormitorios = dormitorios;
    }

    public Integer getBanheiros() {
        return banheiros;
    }
    public void setBanheiros(Integer banheiros) {
        this.banheiros = banheiros;
    }

    public Integer getGaragem() {
        return garagem;
    }
    public void setGaragem(Integer garagem) {
        this.garagem = garagem;
    }

    public BigDecimal getAreaTotal() {
        return areaTotal;
    }
    public void setAreaTotal(BigDecimal areaTotal) {
        this.areaTotal = areaTotal;
    }

    public BigDecimal getAreaConstruida() {
        return areaConstruida;
    }
    public void setAreaConstruida(BigDecimal areaConstruida) {
        this.areaConstruida = areaConstruida;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }
    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Boolean getDestaque() {
        return destaque;
    }
    public void setDestaque(Boolean destaque) {
        this.destaque = destaque;
    }

    /*
        public TipoImovelModel getTipoImovel() {
            return tipoImovel;
        }
        public void setTipoImovel(TipoImovelModel tipoImovel) {
            this.tipoImovel = tipoImovel;
        }

        public BairroModel getBairro() {
            return bairro;
        }
        public void setBairro(BairroModel bairro) {
            this.bairro = bairro;
        }

        public UserModel getUsuario() {
            return usuario;
        }
        public void setUsuario(UserModel usuario) {
            this.usuario = usuario;
        }
    */

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
        ImovelModel other = (ImovelModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }    
}
