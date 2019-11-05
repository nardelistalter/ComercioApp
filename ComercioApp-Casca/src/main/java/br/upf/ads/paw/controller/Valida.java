/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.ads.paw.controller;

import br.upf.ads.paw.controladores.FactoryManager;
import br.upf.ads.paw.entidades.Funcionario;
import br.upf.ads.paw.entidades.Permissao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pavan
 */
public class Valida {
    public static boolean acesso(HttpServletRequest req,
                                 HttpServletResponse res,
                                 String programa) {
        System.out.println(req.getSession().getAttribute("func"));
        if(req.getSession().getAttribute("func")!=null) {
            Funcionario f = (Funcionario) req.getSession().getAttribute("func");
            EntityManager em = FactoryManager.getInstance().getEm();
            System.out.println("f: "+f);
            System.out.println("cat: "+f.getCategoria());
            Query q = em.createQuery("FROM Permissao p WHERE p.programa.nome = :programa and p.categoriaFuncional.id = :cat");
            q.setParameter("programa", programa);
            q.setParameter("cat", f.getCategoria().getId());
            List<Permissao> list = q.getResultList();
            for(Permissao p: list) {
                System.out.println("p: "+p);
            }
            return true;            
        }
        return false;
    }
}
