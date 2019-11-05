package br.upf.ads.paw.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Mior
 */
@Entity
public class CartaoFidelidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_CART_FID", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CART_FID")
    private Long id;

    @Column(nullable = false)
    private Integer vencimento;

    @Column(nullable = false, precision = 2)
    private Double limite;

    @Column(nullable = false, precision = 2)
    private Double qtdPontos;

    @Column(nullable = false, precision = 2)
    private Double fatorConversao;

    @Column(nullable = false)
    private Integer senha;

    @ManyToOne(optional = false)
    private Pessoa cliente;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "cartaoFidelidade")
    private List<Movimento> movimentos;

    public CartaoFidelidade() {
    }

    public CartaoFidelidade(Long id, Integer vencimento, Double limite, Double qtdPontos, Double fatorConversao, Integer senha, Pessoa cliente, List<Movimento> movimentos) {
        this.id = id;
        this.vencimento = vencimento;
        this.limite = limite;
        this.qtdPontos = qtdPontos;
        this.fatorConversao = fatorConversao;
        this.senha = senha;
        this.cliente = cliente;
        this.movimentos = movimentos;
    }

    public Integer getVencimento() {
        return vencimento;
    }

    public void setVencimento(Integer vencimento) {
        this.vencimento = vencimento;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public Double getQtdPontos() {
        return qtdPontos;
    }

    public void setQtdPontos(Double qtdPontos) {
        this.qtdPontos = qtdPontos;
    }

    public Double getFatorConversao() {
        return fatorConversao;
    }

    public void setFatorConversao(Double fatorConversao) {
        this.fatorConversao = fatorConversao;
    }

    public Integer getSenha() {
        return senha;
    }

    public void setSenha(Integer senha) {
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public List<Movimento> getMovimentos() {
        return movimentos;
    }

    public void setMovimentos(List<Movimento> movimentos) {
        this.movimentos = movimentos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CartaoFidelidade)) {
            return false;
        }
        CartaoFidelidade other = (CartaoFidelidade) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Cliente: " + cliente.getNome() + ", Pontos: " + qtdPontos + ", Limite: " + limite;
    }

}
