package models;

import demo.Pessoa;
import demo.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ProdutoModel {

    public void create(Produto produto){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(produto);
            em.getTransaction().commit();
            System.out.println("Produto criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Produto findById(Produto produto) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Produto p = null;

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            p = em.find(Produto.class, produto.getId());
            em.getTransaction().commit();
            System.out.println("Produto encontrado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao econtrar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
        return p;
    }

    public List<Produto> findAll() {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
            EntityManager em = emf.createEntityManager();
            List<Produto> produtos = new ArrayList<Produto>();

            try {
                System.out.println("Iniciando a transação");
                em.getTransaction().begin();
                Query consulta = em.createNativeQuery("SELECT * FROM Produto");
                produtos = consulta.getResultList();
                em.getTransaction().commit();
                System.out.println("Produtos encontrados com sucesso !!!");
            } catch (Exception e) {
                em.close();
                System.err.println("Erro ao criar o produto !!!" + e.getMessage());
            } finally {
                em.close();
                System.out.println("Finalizando a transação");
            }
            return produtos;
        }

    public void update(Produto p){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Produto produto;
        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            produto = em.find(Produto.class, p.getId());
            produto.setNome(p.getNome());
            produto.setQuantidade(p.getQuantidade());
            produto.setPreco(p.getPreco());
            produto.setStatus(p.isStatus());
            em.merge(produto);
            em.getTransaction().commit();
            System.out.println("Produto atualizado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao atualizar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void delete(Produto p){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Produto produto;

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            produto = em.find(Produto.class, p.getId());
            em.remove(produto);
            em.getTransaction().commit();
            System.out.println("Produto deletado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao deletar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }
}
