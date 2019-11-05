package br.upf.ads.paw.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Mior
 */
@Entity
public class ItemVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "SEQ_ITEM", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ITEM")
    private Long id;
    
    @Column(nullable = false, precision = 2)
    private Double qtd;
    
    @Column(nullable = false, precision = 2)
    private Double valorUnitario;
    
    @Column(nullable = false, precision = 2)
    private Double desconto;
    
    @Column(nullable = false, precision = 2)
    private Double valorTotal;
    
    @ManyToOne(optional = false)
    private Produto produto;
    
    @ManyToOne(optional = false)
    private Venda venda;

    public ItemVenda() {}

    public ItemVenda(Long id, Double qtd, Double valorUnitario, Double desconto, Double valorTotal, Produto produto, Venda venda) {
        this.id = id;
        this.qtd = qtd;
        this.valorUnitario = valorUnitario;
        this.desconto = desconto;
        this.valorTotal = valorTotal;
        this.produto = produto;
        this.venda = venda;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQtd() {
        return qtd;
    }

    public void setQtd(Double qtd) {
        this.qtd = qtd;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ItemVenda)) {
            return false;
        }
        ItemVenda other = (ItemVenda) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return produto.toString() + " " + qtd + " " + produto.getUnidade() + " " + valorTotal;
    }
    
}