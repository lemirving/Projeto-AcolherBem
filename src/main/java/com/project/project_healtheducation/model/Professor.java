package com.project.project_healtheducation.model;

import java.util.ArrayList;

public class Professor implements Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private String tipo;
    private String caminhoImagem;


    public Professor() {}


    public Professor(int id, String nome, String email, String senha, String tipo, String caminhoImagem) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.caminhoImagem = caminhoImagem;
    }

    // Construtor para inserção (sem ID, pois o banco gera)
    public Professor(String nome, String email, String senha, String tipo, String caminhoImagem) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.caminhoImagem = caminhoImagem;
    }

    @Override
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Override
    public String getNome() { return nome; }
    @Override
    public void setNome(String nome) { this.nome = nome; }

    @Override
    public String getEmail() { return email; }
    @Override
    public void setEmail(String email) { this.email = email; }

    @Override
    public String getSenha() { return senha; }
    @Override
    public void setSenha(String senha) { this.senha = senha; }


    @Override
    public String getTipo() { return tipo; }
    @Override
    public void setTipo(String tipo) { this.tipo = tipo; }


    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }



}