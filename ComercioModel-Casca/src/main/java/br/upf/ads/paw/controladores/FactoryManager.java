/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.ads.paw.controladores;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jaqson
 */
public class FactoryManager {
    
    private static EntityManagerFactory emf = null;
        
    public EntityManagerFactory getEmf(){
        return emf;
    }

    public EntityManager getEm(){
        return emf.createEntityManager();
    }
    
    
    private FactoryManager() {
        emf = Persistence.createEntityManagerFactory("ComercioMySQLPU");
        //emf = Persistence.createEntityManagerFactory("ComercioPostgreSQLPU");       
    }
    
    public static FactoryManager getInstance() {
        return FactoryManagerHolder.INSTANCE;
    }
    
    private static class FactoryManagerHolder {
        private static final FactoryManager INSTANCE = new FactoryManager();
    }
}
