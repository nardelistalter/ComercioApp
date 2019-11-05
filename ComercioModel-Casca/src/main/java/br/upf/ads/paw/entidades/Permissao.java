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
public class Permissao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_PERM", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PERM")
    private Long id;

    @Column(nullable = false)
    private Boolean alterar;

    @Column(nullable = false)
    private Boolean criar;

    @Column(nullable = false)
    private Boolean excluir;

    @Column(nullable = false)
    private Boolean consultar;

    @ManyToOne(optional = false)
    private Programa programa;

    @ManyToOne(optional = false)
    private CategoriaFuncional categoriaFuncional;

    public Permissao() {
    }

    public Permissao(Long id, Boolean alterar, Boolean criar, Boolean excluir, Boolean consultar, Programa programa, CategoriaFuncional categoriaFuncional) {
        this.id = id;
        this.alterar = alterar;
        this.criar = criar;
        this.excluir = excluir;
        this.consultar = consultar;
        this.programa = programa;
        this.categoriaFuncional = categoriaFuncional;
    }

    public CategoriaFuncional getCategoriaFuncional() {
        return categoriaFuncional;
    }

    public void setCategoriaFuncional(CategoriaFuncional categoriaFuncional) {
        this.categoriaFuncional = categoriaFuncional;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAlterar() {
        return alterar;
    }

    public void setAlterar(Boolean alterar) {
        this.alterar = alterar;
    }

    public Boolean getCriar() {
        return criar;
    }

    public void setCriar(Boolean criar) {
        this.criar = criar;
    }

    public Boolean getExcluir() {
        return excluir;
    }

    public void setExcluir(Boolean excluir) {
        this.excluir = excluir;
    }

    public Boolean getConsultar() {
        return consultar;
    }

    public void setConsultar(Boolean consultar) {
        this.consultar = consultar;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Permissao)) {
            return false;
        }
        Permissao other = (Permissao) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Permissao{" + "id=" + id + ", alterar=" + alterar + ", criar=" + criar + ", excluir=" + excluir + ", consultar=" + consultar + ", programa=" + programa + ", categoriaFuncional=" + categoriaFuncional + '}';
    }

    

}
