package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nomeCompleto;
    @Column
    private String matricula;
    @Column
    private Date nascimento;
    @Column
    private String email;

    @OneToMany(mappedBy = "alunoTelefone", cascade = CascadeType.ALL)
    private Set<Telefone> telefones = new HashSet<>();

    @OneToMany(mappedBy = "alunoEndereco",cascade = CascadeType.ALL)
    private Set<Endereco> enderecos = new HashSet<>();

    @ManyToMany(mappedBy = "alunoCurso", cascade = CascadeType.ALL)
    private Set<Curso> cursos = new HashSet<>();

    public Aluno(){};

    public Aluno(String nomeCompleto, String matricula, Date nascimento, String email, Telefone telefones, Endereco enderecos, Curso cursos) {
        this.nomeCompleto = nomeCompleto;
        this.matricula = matricula;
        this.nascimento = nascimento;
        this.email = email;
        this.telefones = (Set<Telefone>) telefones;
        this.enderecos = (Set<Endereco>) enderecos;
        this.cursos = (Set<Curso>) cursos;
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }
}
