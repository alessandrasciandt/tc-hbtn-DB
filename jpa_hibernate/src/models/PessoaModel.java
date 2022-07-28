package models;

import demo.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class PessoaModel {

    public void create(Pessoa pessoa){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(pessoa);
            em.getTransaction().commit();
            System.out.println("Pessoa criada com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar a pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Pessoa findById(Pessoa pessoa){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Pessoa p = null;

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            p = em.find(Pessoa.class, pessoa.getId());
            em.getTransaction().commit();
            System.out.println("Pessoa encontrada com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao encontrar a pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
        return p;
    }

    public List<Pessoa> findAll(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        List<Pessoa> pessoas = new ArrayList<Pessoa>();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            Query consulta = em.createNativeQuery("SELECT * FROM Pessoa");
            pessoas = consulta.getResultList();
            em.getTransaction().commit();
            System.out.println("Pessoas encontradas com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao encontrar as pessoas !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
        return pessoas;
    }

    public void update(Pessoa pessoa){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Pessoa p;

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            p = em.find(Pessoa.class, pessoa.getId());
            p.setNome(p.getNome());
            p.setEmail(p.getEmail());
            p.setIdade(p.getIdade());
            p.setCpf(p.getCpf());
            p.setDataNascimento(p.getDataNascimento());
            em.merge(p);
            em.getTransaction().commit();
            System.out.println("Pessoa atualizada com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao atualizar a pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void delete(Pessoa p){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Pessoa pessoa;

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            pessoa = em.find(Pessoa.class, p.getId());
            em.remove(pessoa);
            em.getTransaction().commit();
            System.out.println("Pessoa deletada com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao deletar a pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }

    }
}
