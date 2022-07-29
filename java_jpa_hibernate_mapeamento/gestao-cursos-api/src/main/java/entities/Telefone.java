package entities;

import javax.persistence.*;

@Entity
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String DDD;
    @Column
    private String numero;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno alunoTelefone;


    public Telefone(){}

    public Telefone(String DDD, String numero) {
        this.DDD = DDD;
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDDD() {
        return DDD;
    }

    public void setDDD(String DDD) {
        this.DDD = DDD;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Aluno getAlunoTelefone() {
        return alunoTelefone;
    }

    public void setAlunoTelefone(Aluno alunoTelefone) {
        this.alunoTelefone = alunoTelefone;
    }
}
