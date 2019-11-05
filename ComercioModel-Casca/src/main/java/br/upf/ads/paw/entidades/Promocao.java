package br.upf.ads.paw.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Mior
 */
@Entity
public class Promocao implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "SEQ_PROMO", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROMO")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date inicio;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date fim;
    
    @Column(nullable = false, precision = 2)
    private Double porcentualDesconto;
    
    @Column(nullable = false)
    private Boolean soFidelidade;

    @ManyToOne(optional = false)
    private Produto produto;
            
    public Promocao() {}

    public Promocao(Long id, Date inicio, Date fim, Double porcentualDesconto, Boolean soFidelidade, Produto produto) {
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;
        this.porcentualDesconto = porcentualDesconto;
        this.soFidelidade = soFidelidade;
        this.produto = produto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public Double getPorcentualDesconto() {
        return porcentualDesconto;
    }

    public void setPorcentualDesconto(Double porcentualDesconto) {
        this.porcentualDesconto = porcentualDesconto;
    }

    public Boolean getSoFidelidade() {
        return soFidelidade;
    }

    public void setSoFidelidade(Boolean soFidelidade) {
        this.soFidelidade = soFidelidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Promocao)) {
            return false;
        }
        Promocao other = (Promocao) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }
    
}