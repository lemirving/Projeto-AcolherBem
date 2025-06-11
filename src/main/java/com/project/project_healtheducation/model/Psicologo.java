package com.project.project_healtheducation.model;

import java.util.ArrayList;

public class Psicologo {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private int idade;
    private String identificacao;
    private ArrayList<Turma> turmasAcompanhadas = new ArrayList<>();

    public Psicologo(int id, String nome, String email, String senha, int idade, String identificacao, ArrayList<Turma> turmasAcompanhadas) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
        this.identificacao = identificacao;
        this.turmasAcompanhadas = turmasAcompanhadas;
    }
    public Psicologo( String nome, String email, String senha, int idade, String identificacao, ArrayList<Turma> turmasAcompanhadas) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
        this.identificacao = identificacao;
        this.turmasAcompanhadas = turmasAcompanhadas;
    }
    public Psicologo(){

    }
    public Psicologo(int id, String nome, String email, int idade, String identificacao, ArrayList<Turma> turmasAcompanhadas) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.identificacao = identificacao;
        this.turmasAcompanhadas = turmasAcompanhadas;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public ArrayList<Turma> getTurmasAcompanhadas() {
        return turmasAcompanhadas;
    }

    public void addTurma(Turma novaTurma) {
        this.turmasAcompanhadas.add(novaTurma);
    }

    public void rmvTurma(Turma turmaRemovida){
        this.turmasAcompanhadas.remove(turmaRemovida);
    }

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public void setTurmasAcompanhadas(ArrayList<Turma> turmasAcompanhadas) {
        this.turmasAcompanhadas = turmasAcompanhadas;
    }
}
