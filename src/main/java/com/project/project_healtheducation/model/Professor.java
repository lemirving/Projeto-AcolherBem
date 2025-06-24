package com.project.project_healtheducation.model;

import java.util.ArrayList;

public class Professor {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private int idade;
    private String especialidade;
    private String tipo;
    private ArrayList<Turma> turmasLecionadas = new ArrayList<>();

    public Professor(){

    }

    public Professor(String nome, String email, String senha, int idade, String especialidade, ArrayList<Turma> turmasLecionadas) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
        this.especialidade = especialidade;
        this.turmasLecionadas = turmasLecionadas;
    }

    public Professor(int id, String nome, String email, String senha, int idade, String especialidade, ArrayList<Turma> turmasLecionadas) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
        this.especialidade = especialidade;
        this.turmasLecionadas = turmasLecionadas;
    }
    public Professor(int id, String nome, String email, int idade, String especialidade, ArrayList<Turma> turmasLecionadas) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.especialidade = especialidade;
        this.turmasLecionadas = turmasLecionadas;
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

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void setTurmasLecionadas(ArrayList<Turma> turmasLecionadas) {
        this.turmasLecionadas = turmasLecionadas;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public ArrayList<Turma> getTurmasLecionadas() {
        return turmasLecionadas;
    }

    public void adicionarTurma(Turma novaTurma) {
        this.turmasLecionadas.add(novaTurma);
    }

    public void removerTurma(Turma turmaRemovida){
        this.turmasLecionadas.remove(turmaRemovida);
    }

    public String getTipo() {
        return tipo;
    }
}
