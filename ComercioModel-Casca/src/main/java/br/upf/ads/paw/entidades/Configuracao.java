package br.upf.ads.paw.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author Mior
 */
@Entity
public class Configuracao extends PJuridica implements Serializable {

    @Column(nullable = false)
    private String logo;
    
    @Column(nullable = false, length = 32)
    private String senha;

    public Configuracao() {}

    public Configuracao(String logo, String senha, String cnpj, String ie, String nomeFantasia, List<Produto> produtos, Long id, String nome, String logradouro, String numero, String complemento, String bairro, String email, String telefone, String cep, Cidade cidade) {
        super(cnpj, ie, nomeFantasia, produtos, id, nome, logradouro, numero, complemento, bairro, email, telefone, cep, cidade);
        this.logo = logo;
        this.senha = senha;
    }

    
 
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}