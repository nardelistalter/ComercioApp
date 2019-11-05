package br.upf.ads.paw.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Mior
 */
@Entity
public class PFisica extends Pessoa implements Serializable {

    @Column(length = 14, nullable = false, unique = true)
    private String cpf;
    
    @Column(length = 10, nullable = false)
    private String rg;
    
    @Column(length = 1, nullable = false)
    private Character sexo;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date nascimento;

    public PFisica() {}

    public PFisica(String cpf, String rg, Character sexo, Date nascimento, Long id, String nome, String logradouro, String numero, String complemento, String bairro, String email, String telefone, String cep, Cidade cidade) {
        super(id, nome, logradouro, numero, complemento, bairro, email, telefone, cep, cidade);
        this.cpf = cpf;
        this.rg = rg;
        this.sexo = sexo;
        this.nascimento = nascimento;
    }

    

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }
    
}