package com.project.project_healtheducation.model;

import java.util.ArrayList;

public class Professor implements Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private int idade; // Apenas na aplicação (não está no banco ainda)
    private String especialidade; // Apenas na aplicação
    private String tipo;

    private ArrayList<Turma> turmasLecionadas = new ArrayList<>();

    // Construtores
    public Professor() {}

    public Professor(String nome, String email, String senha, int idade, String especialidade, String tipo) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
        this.especialidade = especialidade;
        this.tipo = tipo;
    }

    public Professor(int id, String nome, String email, String senha, int idade, String especialidade, String tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
        this.especialidade = especialidade;
        this.tipo = tipo;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public ArrayList<Turma> getTurmasLecionadas() { return turmasLecionadas; }
    public void setTurmasLecionadas(ArrayList<Turma> turmasLecionadas) { this.turmasLecionadas = turmasLecionadas; }

    public void adicionarTurma(Turma novaTurma) {
        this.turmasLecionadas.add(novaTurma);
    }

    public void removerTurma(Turma turmaRemovida) {
        this.turmasLecionadas.remove(turmaRemovida);
    }
}
