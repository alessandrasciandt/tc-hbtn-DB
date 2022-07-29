package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nomeCompleto;
    @Column
    private String matriculo;
    @Column
    private String email;

    @OneToMany(mappedBy = "cursoProfessor")
    private Set<Curso> cursos = new HashSet<>();

    public Professor(){}

    public Professor(String nomeCompleto, String matriculo, String email, Set<Curso> cursos) {
        this.nomeCompleto = nomeCompleto;
        this.matriculo = matriculo;
        this.email = email;
        this.cursos = cursos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getMatriculo() {
        return matriculo;
    }

    public void setMatriculo(String matriculo) {
        this.matriculo = matriculo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }
}
