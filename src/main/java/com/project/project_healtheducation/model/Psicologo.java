package com.project.project_healtheducation.model;

import java.util.ArrayList;

public class Psicologo implements Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String idade;
    private String tipo;
    private String identificacao;
    private ArrayList<Turma> turmasAcompanhadas = new ArrayList<>();

    // Construtor padrão
    public Psicologo() {}

    // Construtor completo com 'tipo'
    public Psicologo(int id, String nome, String email, String senha, String idade, String tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
        this.tipo = tipo;
    }

    // Construtor sem id, com 'tipo'
    public Psicologo(String nome, String email, String senha, String idade, String tipo) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
        this.tipo = tipo;
    }

    // Construtor sem senha (ex: para consultas)
    public Psicologo(int id, String nome, String email, String idade, String tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.tipo = tipo;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getIdade() {
        return idade;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public ArrayList<Turma> getTurmasAcompanhadas() {
        return turmasAcompanhadas;
    }

    public void setTurmasAcompanhadas(ArrayList<Turma> turmasAcompanhadas) {
        this.turmasAcompanhadas = turmasAcompanhadas;
    }

    // Métodos para adicionar e remover turma
    public void addTurma(Turma turma) {
        if (turma != null && !this.turmasAcompanhadas.contains(turma)) {
            this.turmasAcompanhadas.add(turma);
        }
    }

    public void removeTurma(Turma turma) {
        this.turmasAcompanhadas.remove(turma);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
