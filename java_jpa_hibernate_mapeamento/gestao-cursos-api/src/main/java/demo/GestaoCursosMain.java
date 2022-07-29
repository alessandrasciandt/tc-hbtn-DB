package demo;

import entities.*;
import models.AlunoModel;
import models.CursoModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GestaoCursosMain {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        AlunoModel alunoModel = new AlunoModel();

        //Endereço
        Endereco endereco1 = new Endereco();
        endereco1.setLogadouro("00000");
        endereco1.setEndereco("Rua Sebastoão");
        endereco1.setNumero("360");
        endereco1.setBairro("Cidade Nova");
        endereco1.setCidade("Manaus");
        endereco1.setEstado("Amazonas");
        endereco1.setCep(6909490);

        //set de Endereço
        Set<Endereco> enderecos = new HashSet<>();
        enderecos.add(endereco1);

        //Telefone
        Telefone telefone1 = new Telefone();
        telefone1.setDDD("92");
        telefone1.setNumero("993433044");

        //Set de Telefones
        Set<Telefone> telefones = new HashSet<>();
        telefones.add(telefone1);

        //Alunes
        Aluno aln = new Aluno();
        aln.setNomeCompleto("Lulu Mendes");
        aln.setMatricula("01010101");
        aln.setNascimento(date.parse("22/06/2020"));
        aln.setEmail("lulu@lulu.com");
        aln.getTelefones().add(telefone1);
        aln.getEnderecos().add(endereco1);
        telefone1.setAlunoTelefone(aln);
        endereco1.setAlunoEndereco(aln);


        //Criando Aluno
        alunoModel.create(aln);

        //Atualizar ALuno
        aln.setEmail("lulu2@gmail.com");
        aln.setMatricula("02020202");
        alunoModel.update(aln);

        //BUscar Alune
        Aluno aluno2 = alunoModel.findById(aln);

        //Buscar Todos alunes
        List<Aluno> aluno3 = alunoModel.findAll();

        //Deletar alune
        alunoModel.delete(aln);

        //---========---

        CursoModel cursoModel = new CursoModel();

        //Professor
        Professor professor = new Professor();
        professor.setNomeCompleto("Simone Bouvoir");
        professor.setMatriculo("9990000");
        professor.setEmail("simoninha@gmail.com");

        //Material do Curso
        MaterialCurso materialCurso = new MaterialCurso();
        materialCurso.setUrl("UrlCaminhoMaterial");

        //Curso
        Curso curso = new Curso();
        curso.setNome("Frances");
        curso.setSigla("FR");
        curso.setMaterialCurso(materialCurso);
        curso.getAlunoCurso().add(aln);
        curso.setCursoProfessor(professor);
        materialCurso.setCurso(curso);
        professor.getCursos().add(curso);
        aln.getCursos().add(curso);

        //criar um curso
        cursoModel.create(curso);

        // Atualizar curso
        curso.setSigla("PY");
        curso.setNome("Phyton");
        cursoModel.update(curso);

        // Encontrar curso
        Curso curso1 = cursoModel.findById(curso);

        // Encontrar cursos
        List<Curso> cursos = cursoModel.findAll();

        // Deletar curso
        cursoModel.delete(curso);
    }
}
