package com.example;

import java.util.ArrayList;
import java.util.List;

public class Bibliotecario {
    private String nome;
    private String identificacao;
    private List<String> atividades;

    public Bibliotecario(String nome, String identificacao) {
        this.nome = nome;
        this.identificacao = identificacao;
        this.atividades = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public List<String> getAtividades() {
        return atividades;
    }
    
    public void cadastrarLivro(Livro livro) {
        String atividade = "Cadastro do livro " + livro.getTitulo();
        this.atividades.add(atividade);
    }
    
    public boolean autenticar(String nome, String identificacao) {
        return this.nome.equals(nome) && this.identificacao.equals(identificacao);
    }

    public void realizarEmprestimo(Livro livro, String usuario) {
        // Lógica para realizar o empréstimo do livro para o usuário
        String atividade = "Empréstimo do livro " + livro.getTitulo() + " para o usuário " + usuario;
        this.atividades.add(atividade);
    }

    public boolean podeRealizarDevolucao(Livro livro) {
        // Lógica para verificar se o bibliotecário pode realizar a devolução do livro
        return true; // Implementação de exemplo, pode ser modificada conforme a lógica real
    }

    public void registrarPerda(Livro livro) {
        String atividade = "Registro de perda do livro " + livro.getTitulo();
        this.atividades.add(atividade);
    }
}