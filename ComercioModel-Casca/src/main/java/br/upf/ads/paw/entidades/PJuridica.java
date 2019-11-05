package br.upf.ads.paw.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author Mior
 */
@Entity
public class PJuridica extends Pessoa implements Serializable {

    @Column(length = 18, nullable = false, unique = true)
    private String cnpj;

    @Column(length = 15, nullable = false, unique = true)
    private String ie;

    @Column(length = 50, nullable = false, unique = true)
    private String nomeFantasia;

    @ManyToMany
    private List<Produto> produtos;

    public PJuridica() {
    }

    public PJuridica(String cnpj, String ie, String nomeFantasia, List<Produto> produtos, Long id, String nome, String logradouro, String numero, String complemento, String bairro, String email, String telefone, String cep, Cidade cidade) {
        super(id, nome, logradouro, numero, complemento, bairro, email, telefone, cep, cidade);
        this.cnpj = cnpj;
        this.ie = ie;
        this.nomeFantasia = nomeFantasia;
        this.produtos = produtos;
    }
    
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

}
