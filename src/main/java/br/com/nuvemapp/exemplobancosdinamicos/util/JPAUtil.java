/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nuvemapp.exemplobancosdinamicos.util;

import java.util.ArrayList;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.ejb.Ejb3Configuration;

public class JPAUtil {

    @SuppressWarnings("deprecation")
	public EntityManager getEntityManagerDinamica(
            String PuName,
            String name,
            String url,
            String user, 
            String password, 
            String driver, 
            String dialect, 
            String show_sql, 
            String format_sql, 
            String hbm2ddl, 
            ArrayList<Class> classes) {
        Properties properties = new Properties();
        properties.put("javax.persistence.provider", "org.hibernate.ejb.HibernatePersistence");
	properties.put("javax.persistence.transactionType", "RESOURCE_LOCAL");
        properties.put("hibernate.ejb.entitymanager_factory_name", name);
        properties.put("javax.persistence.jdbc.url", url);
        properties.put("javax.persistence.jdbc.user", user);
        properties.put("javax.persistence.jdbc.password", password);
        properties.put("javax.persistence.jdbc.driver", driver);
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.show_sql", show_sql);
        properties.put("hibernate.format_sql", format_sql);
        properties.put("hibernate.hbm2ddl.auto", hbm2ddl);

        Ejb3Configuration cfg = new Ejb3Configuration();
        for (Class classe : classes) {
            cfg.addAnnotatedClass(classe);
        }
        cfg.configure(PuName, properties);

        EntityManagerFactory factory = cfg.buildEntityManagerFactory();
        return factory.createEntityManager();
    }

}
