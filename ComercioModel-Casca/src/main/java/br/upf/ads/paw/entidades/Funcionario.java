package br.upf.ads.paw.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Mior
 */
@Entity
public class Funcionario extends PFisica implements Serializable {
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date admissao;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date demissao;
    
    @Column(length = 14, nullable = false, unique = true)
    private String ctps;
    
    @Column(precision = 2, nullable = false)
    private Double salario;
    
    @Column(length = 50, nullable = false, unique = true)
    private String login;
    
    @Column(length = 32, nullable = false)
    private String senha;
    
    @ManyToOne(optional = false)
    private CategoriaFuncional categoria;

    public Funcionario() {}

    public Funcionario(Date admissao, Date demissao, String ctps, Double salario, String login, String senha, CategoriaFuncional categoria, String cpf, String rg, Character sexo, Date nascimento, Long id, String nome, String logradouro, String numero, String complemento, String bairro, String email, String telefone, String cep, Cidade cidade) {
        super(cpf, rg, sexo, nascimento, id, nome, logradouro, numero, complemento, bairro, email, telefone, cep, cidade);
        this.admissao = admissao;
        this.demissao = demissao;
        this.ctps = ctps;
        this.salario = salario;
        this.login = login;
        this.senha = senha;
        this.categoria = categoria;
    }

    
    public Date getAdmissao() {
        return admissao;
    }

    public void setAdmissao(Date admissao) {
        this.admissao = admissao;
    }

    public Date getDemissao() {
        return demissao;
    }

    public void setDemissao(Date demissao) {
        this.demissao = demissao;
    }

    public String getCtps() {
        return ctps;
    }

    public void setCtps(String ctps) {
        this.ctps = ctps;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public CategoriaFuncional getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaFuncional categoria) {
        this.categoria = categoria;
    }
    
}