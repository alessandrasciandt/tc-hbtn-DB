package models;

import entities.Aluno;
import entities.Curso;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class AlunoModel {

    public void create(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager entityManager = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            entityManager.getTransaction().begin();
            entityManager.persist(aluno.getEnderecos());
            entityManager.persist(aluno.getMatricula());
            entityManager.persist(aluno.getTelefones());
            entityManager.persist(aluno);
            entityManager.getTransaction().commit();
            System.out.println("Alune criade com sucesso!");
        } catch (Exception e) {
            entityManager.close();
            System.out.println("Erro ao criar Alune");
        } finally {
            entityManager.close();
            System.out.println("Finalizada a transação");
        }
    }

    public Aluno findById(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager entityManager = emf.createEntityManager();
        Aluno aln = null;

        try {
            System.out.println("Iniciando a transação");
            entityManager.getTransaction().begin();
            aln = entityManager.find(Aluno.class, aluno.getId());
            entityManager.getTransaction().commit();
            System.out.println("Alune encontrade com sucesso");
        } catch (Exception e) {
            entityManager.close();
            System.out.println("Erro ao encontrar alune" + e.getMessage());
        } finally {
            entityManager.close();
            System.out.println("Finalizada a transação");
        }
        return aln;
    }

    public List<Aluno> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        List<Aluno> alunos = new ArrayList<Aluno>();
        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            Query query = em.createNativeQuery("SELECT * FROM Aluno");
            alunos = query.getResultList();
            em.getTransaction().commit();
            System.out.println("Alunes encontrades com sucesso!!");
        } catch (Exception e){
            em.close();
            System.out.println("Erro ao carregador alunes " + e.getMessage());
        }finally {
            em.close();
            System.out.println("Finalizada a transação");
        }
        return alunos;
    }

    public void update(Aluno aluno){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        Aluno aln;

        try{
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            aln = em.find(Aluno.class, aluno.getId());
            aln.setNomeCompleto(aluno.getNomeCompleto());
            aln.setMatricula(aluno.getMatricula());
            aln.setNascimento(aluno.getNascimento());
            aln.setEmail(aluno.getEmail());
            aln.setTelefones(aluno.getTelefones());
            aln.setEnderecos(aluno.getEnderecos());
            aln.setCursos(aluno.getCursos());
            em.merge(aluno);
            em.getTransaction().commit();
            System.out.println("Alune atualizade com sucesso!");
        }catch (Exception e){
            em.close();
            System.out.println("Erro ao editar alune" + e.getMessage());
        }finally {
            em.close();
            System.out.println("Finalizada a transação");
        }
    }

    public void delete(Aluno aluno){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        Aluno aln;

        try{
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            aln = em.find(Aluno.class, aluno.getId());
            em.remove(aln);
            em.getTransaction().commit();
            System.out.println("Alune removide com sucesso!");
        }catch (Exception e){
            em.close();
            System.out.println("Erro ao remover alune"+e.getMessage());
        }finally {
            em.close();
            System.out.println("Finalizando transação");
        }
    }
}
