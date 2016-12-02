package br.com.nuvemapp.exemplobancosdinamicos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.nuvemapp.exemplobancosdinamicos.model.Endereco;
import br.com.nuvemapp.exemplobancosdinamicos.model.Pessoa;
import br.com.nuvemapp.exemplobancosdinamicos.util.JPAUtil;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Class> classes1 = new ArrayList<Class>();
        classes1.add(Pessoa.class);
        JPAUtil util1 = new JPAUtil();
        EntityManager entityManagerDinamica1 = util1.getEntityManagerDinamica(
                "PuDinamico1",
                "db_dinamico01",
                "jdbc:mysql://localhost/db_dinamico01",
                "root",
                "root",
                "com.mysql.jdbc.Driver",
                "org.hibernate.dialect.MySQL5Dialect",
                "true",
                "true",
                "update",
                classes1);
        EntityTransaction transaction1 = entityManagerDinamica1.getTransaction();
        transaction1.begin();

        ArrayList<Class> classes2 = new ArrayList<Class>();
        classes2.add(Endereco.class);
        JPAUtil util2 = new JPAUtil();
        EntityManager entityManagerDinamica2 = util2.getEntityManagerDinamica(
                "PuDinamico2",
                "db_dinamico02",
                "jdbc:mysql://localhost/db_dinamico02",
                "root",
                "root",
                "com.mysql.jdbc.Driver",
                "org.hibernate.dialect.MySQL5Dialect",
                "true",
                "true",
                "update",
                classes2);
        EntityTransaction transaction2 = entityManagerDinamica2.getTransaction();
        transaction2.begin();

        List<Pessoa> resultList1 = entityManagerDinamica1.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
        List<Endereco> resultList2 = entityManagerDinamica2.createQuery("SELECT e FROM Endereco e", Endereco.class).getResultList();
        
        System.out.println("Pessoas:");
        for (Pessoa pessoa : resultList1) {
            System.out.println(pessoa.getId() + " - " + pessoa.getNome());
        }
        
        System.out.println();
        
        System.out.println("Endereços:");
        for (Endereco endereco : resultList2) {
            System.out.println(endereco.getId() + " - " + endereco.getRua() + ", " + endereco.getNumero());
        }
        
        entityManagerDinamica1.close();
        entityManagerDinamica2.close();

    }

}
