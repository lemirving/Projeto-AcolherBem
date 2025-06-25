package com.project.project_healtheducation.model;

import java.util.ArrayList;

public class Psicologo {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private int idade;

    public Psicologo(int id, String nome, String email, String senha, int idade, ArrayList<Turma> turmas) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
    }
    public Psicologo( String nome, String email, String senha, int idade) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
    }
    public Psicologo(int id, String nome, String email, int idade, ArrayList<Turma> turmas){

    }
    public Psicologo(int id, String nome, String email, int idade) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
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


}
