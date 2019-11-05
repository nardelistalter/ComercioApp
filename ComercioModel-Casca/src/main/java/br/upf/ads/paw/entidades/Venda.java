package br.upf.ads.paw.entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Mior
 */
@Entity
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "SEQ_VENDA", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VENDA")
    private Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date data;
    
    @Column(length = 14, nullable = false)
    private String cpfNaNota;
    
    @Column(precision = 2, nullable = false)
    private Double totalVenda;
    
    @ManyToOne(optional = false)
    private Pessoa pessoa;
    
    @OneToMany(cascade = CascadeType.ALL, 
               mappedBy = "venda")
    private List<ItemVenda> itens;
    
    @OneToMany(cascade = CascadeType.ALL, 
               mappedBy = "venda")
    private List<Pagamento> pagamentos;

    @ManyToOne
    private CartaoFidelidade cartaoFidelidade;
    
    public Venda() {}

    public Venda(Long id, Date data, String cpfNaNota, Double totalVenda, Pessoa pessoa, List<ItemVenda> itens, List<Pagamento> pagamentos, CartaoFidelidade cartaoFidelidade) {
        this.id = id;
        this.data = data;
        this.cpfNaNota = cpfNaNota;
        this.totalVenda = totalVenda;
        this.pessoa = pessoa;
        this.itens = itens;
        this.pagamentos = pagamentos;
        this.cartaoFidelidade = cartaoFidelidade;
    }

    public CartaoFidelidade getCartaoFidelidade() {
        return cartaoFidelidade;
    }

    public void setCartaoFidelidade(CartaoFidelidade cartaoFidelidade) {
        this.cartaoFidelidade = cartaoFidelidade;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getCpfNaNota() {
        return cpfNaNota;
    }

    public void setCpfNaNota(String cpfNaNota) {
        this.cpfNaNota = cpfNaNota;
    }

    public Double getTotalVenda() {
        return totalVenda;
    }

    public void setTotalVenda(Double totalVenda) {
        this.totalVenda = totalVenda;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "br.upf.casca.ads.paoo.entidades.Venda[ id=" + id + " ]";
    }
    
}