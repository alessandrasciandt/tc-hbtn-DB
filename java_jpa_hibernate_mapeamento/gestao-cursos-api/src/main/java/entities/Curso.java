package entities;

import javax.naming.StringRefAddr;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

     @Entity
     public class Curso {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     @Column
     private String nome;
     @Column
     private String sigla;

     @ManyToMany
     @JoinTable(name = "aluno_curso", joinColumns = @JoinColumn(name = "aluno_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "curso_id", referencedColumnName = "id"))
     private Set<Aluno> alunoCurso = new HashSet<>();

     @ManyToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "professor_id")
     private Professor cursoProfessor;

     @OneToOne
     @JoinColumn(name = "material_id", referencedColumnName = "id")
     private MaterialCurso materialCurso;


     public Curso(){}

     public Curso(String nome, String sigla, Set<Aluno> alunoCurso, Professor cursoProfessor, MaterialCurso materialCurso) {
          this.nome = nome;
          this.sigla = sigla;
          this.alunoCurso = alunoCurso;
          this.cursoProfessor = cursoProfessor;
          this.materialCurso = materialCurso;
     }

     public Long getId() {
          return id;
     }

     public void setId(Long id) {
          this.id = id;
     }

     public String getNome() {
          return nome;
     }

     public void setNome(String nome) {
          this.nome = nome;
     }

     public String getSigla() {
          return sigla;
     }

     public void setSigla(String sigla) {
          this.sigla = sigla;
     }

          public Set<Aluno> getAlunoCurso() {
               return alunoCurso;
          }

          public void setAlunoCurso(Set<Aluno> alunoCurso) {
               this.alunoCurso = alunoCurso;
          }

          public Professor getCursoProfessor() {
               return cursoProfessor;
          }

          public void setCursoProfessor(Professor cursoProfessor) {
               this.cursoProfessor = cursoProfessor;
          }

          public MaterialCurso getMaterialCurso() {
               return materialCurso;
          }

          public void setMaterialCurso(MaterialCurso materialCurso) {
               this.materialCurso = materialCurso;
          }

     }
