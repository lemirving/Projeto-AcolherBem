package com.project.project_healtheducation.model;

import java.util.ArrayList; // ArrayList import is not strictly needed if turmasLecionadas is removed, but harmless if kept.

public class Psicologo implements Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    // REMOVIDO: private String idade; // Idade não é um atributo para Psicólogo
    private String tipo;
    private String caminhoImagem;

    // --- Construtores ---

    // Construtor padrão (sempre bom ter para frameworks)
    public Psicologo() {}

    // Construtor completo para buscar ou criar Psicólogo com todos os dados do banco
    // ATENÇÃO: Assegure-se de que a ordem e o tipo dos parâmetros correspondem ao seu DAO
    // e às colunas da sua tabela 'psicologo' no banco de dados.
    public Psicologo(int id, String nome, String email, String senha, String tipo, String caminhoImagem) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.caminhoImagem = caminhoImagem;
    }

    // Construtor para inserção (sem id, pois o banco de dados o gera)
    public Psicologo(String nome, String email, String senha, String tipo, String caminhoImagem) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.caminhoImagem = caminhoImagem;
    }

    // --- Getters e Setters ---

    @Override // Indica que este método está implementando um método da interface Usuario
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getSenha() {
        return senha;
    }

    @Override
    public void setSenha(String senha) {
        this.senha = senha;
    }

    // REMOVIDO: getIdade() e setIdade() - não são mais relevantes para Psicólogo
    // @Override // Remover o @Override daqui, pois 'idade' não está na interface
    // public String getIdade() { return idade; }
    // public void setIdade(String idade) { this.idade = idade; }

    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    @Override
    public String getCaminhoImagem() {
        return caminhoImagem;
    }
    @Override
    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }
}