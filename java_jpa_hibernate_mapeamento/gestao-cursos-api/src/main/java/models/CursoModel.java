package models;

import entities.Curso;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class CursoModel {

    public void create(Curso curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager entityManager = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            entityManager.getTransaction().begin();
            entityManager.persist(curso.getMaterialCurso());
            entityManager.persist(curso.getCursoProfessor());
            entityManager.persist(curso);
            entityManager.getTransaction().commit();
            System.out.println("Curso criado com sucesso!");
        } catch (Exception e) {
            entityManager.close();
            System.out.println("Erro ao criar o curso");
        } finally {
            entityManager.close();
            System.out.println("Finalizada a transação");
        }
    }

    public Curso findById(Curso cursos) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager entityManager = emf.createEntityManager();
        Curso curso = null;

        try {
            System.out.println("Iniciando a transação");
            entityManager.getTransaction().begin();
            curso = entityManager.find(Curso.class, cursos.getId());
            entityManager.getTransaction().commit();
            System.out.println("Curso encontrado com sucesso");
        } catch (Exception e) {
            entityManager.close();
            System.out.println("Erro ao encontrar curso" + e.getMessage());
        } finally {
            entityManager.close();
            System.out.println("Finalizada a transação");
        }
        return curso;
    }

    public List<Curso> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        List<Curso> cursos = new ArrayList<Curso>();
        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            Query query = em.createNativeQuery("SELECT * FROM Curso");
            cursos = query.getResultList();
            em.getTransaction().commit();
            System.out.println("Cursos encontrados com sucesso!!");
        } catch (Exception e){
            em.close();
            System.out.println("Erro ao carregador os cursos " + e.getMessage());
        }finally {
            em.close();
            System.out.println("Finalizada a transação");
        }
        return cursos;
    }

    public void update(Curso cursos){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        Curso curso;

        try{
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            curso = em.find(Curso.class, cursos.getId());
            curso.setNome(curso.getNome());
            curso.setSigla(curso.getSigla());
            em.merge(curso);
            em.getTransaction().commit();
            System.out.println("Curso atualizado com sucesso!");
        }catch (Exception e){
            em.close();
            System.out.println("Erro ao editar curso" + e.getMessage());
        }finally {
            em.close();
            System.out.println("Finalizada a transação");
        }
    }


    public void delete(Curso curso){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        Curso curso1;

        try{
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            curso1 = em.find(Curso.class, curso.getId());
            em.remove(curso1);
            em.getTransaction().commit();
            System.out.println("Curso removido com sucesso!");
        }catch (Exception e){
            em.close();
            System.out.println("Erro ao remover curso"+e.getMessage());
        }finally {
            em.close();
            System.out.println("Finalizando transação");
        }
    }
}
