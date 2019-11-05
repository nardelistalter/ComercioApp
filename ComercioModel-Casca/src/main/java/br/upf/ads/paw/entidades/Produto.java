package br.upf.ads.paw.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Mior
 */
@Entity
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "SEQ_PRODUTO", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUTO")
    private Long id;
    
    @Column(length = 50, nullable = false)
    private String descricao;
    
    @Column(length = 10, nullable = false)
    private String unidade;
    
    @Column(precision = 2, nullable = false)
    private Double estoqueAtual;
    
    @Column(precision = 2, nullable = false)
    private Double estoqueMinimo;
    
    @Column(precision = 2, nullable = false)
    private Double estoqueMaximo;
    
    @Column(precision = 2, nullable = false)
    private Double precoVenda;
    
    @Column(precision = 2, nullable = false)
    private Double margemLucro;
    
    @ManyToOne(optional = false)
    private Categoria categoria;
    
    @ManyToMany(mappedBy = "produtos")
    private List<PJuridica> fornecedores;
    
    public Produto () {}

    public Produto(Long id, String descricao, String unidade, Double estoqueAtual, Double estoqueMinimo, Double estoqueMaximo, Double precoVenda, Double margemLucro, Categoria categoria, List<PJuridica> fornecedores) {
        this.id = id;
        this.descricao = descricao;
        this.unidade = unidade;
        this.estoqueAtual = estoqueAtual;
        this.estoqueMinimo = estoqueMinimo;
        this.estoqueMaximo = estoqueMaximo;
        this.precoVenda = precoVenda;
        this.margemLucro = margemLucro;
        this.categoria = categoria;
        this.fornecedores = fornecedores;
    }

    public List<PJuridica> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<PJuridica> fornecedores) {
        this.fornecedores = fornecedores;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Double getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(Double estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    public Double getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(Double estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public Double getEstoqueMaximo() {
        return estoqueMaximo;
    }

    public void setEstoqueMaximo(Double estoqueMaximo) {
        this.estoqueMaximo = estoqueMaximo;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Double getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(Double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return descricao;
    }
    
}