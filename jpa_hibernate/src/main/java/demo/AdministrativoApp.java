package demo;

import entities.Pessoa;
import entities.Produto;
import models.PessoaModel;
import models.ProdutoModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class AdministrativoApp {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
        ProdutoModel produtoModel = new ProdutoModel();

        Produto p1 = new Produto();
        p1.setNome("TV");
        p1.setPreco(300.0);
        p1.setQuantidade(100);
        p1.setStatus(true);

        // 1) Criando um produto
        produtoModel.create(p1);

        //2) Buscando todos os produtos na base de dados
        List<Produto> produtos = produtoModel.findAll();
        System.out.println("Qtde de produtos encontrados : " + produtos.size());

        //3) BUsca um unico produto na base de dados
        Produto produto = produtoModel.findById(p1);

        //4) Atualizar produto no Banco de Dados
        p1.setNome("Computador");
        p1.setPreco(2500.00);
        p1.setQuantidade(16);
        p1.setStatus(true);
        produtoModel.update(p1);

        //5) Deletar o produto da base de dados
        produtoModel.delete(p1);

        //---==== Pessoa ====---
        PessoaModel pessoaModel = new PessoaModel();

        Pessoa p2 = new Pessoa();
        p2.setNome("Lulu");
        p2.setIdade(2);
        p2.setDataNascimento(data.parse("22/06/2020"));
        p2.setEmail("luluzinho@gmail.com");
        p2.setCpf("20034567890");

        // 1) Criando um produto
        pessoaModel.create(p2);

        //2) Buscando todos os produtos na base de dados
       List<Pessoa> pessoas = pessoaModel.findAll();
        System.out.println("Qtde de pessoas encontradas : " + pessoas.size());

        //3) BUsca um unico produto na base de dados
        Pessoa pessoa = pessoaModel.findById(p2);

        //4) Atualizar produto no Banco de Dados
        p2.setNome("Luluzinho");
        p2.setCpf("010101010101");
        p2.setEmail("luluzinho@gmail.com");
        p2.setIdade(2);
        p2.setDataNascimento(data.parse("22/06/2022"));
        pessoaModel.update(p2);
        //5) Deletar o produto da base de dados
        pessoaModel.delete(p2);
    }
}
