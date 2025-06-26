package com.project.project_healtheducation.model;

import java.util.ArrayList;

public class Aluno implements Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String idade;
    private String nomeTurma;
    private String tipo;
    private String matricula;
    private String humorAtual;
    private String descricaoAluno;
    private String caminhoImagem;

    @Override
    public String getCaminhoImagem() {
        return caminhoImagem;
    }
    @Override
    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }


    public Aluno(String nome, String email, String senha, String idade, String nomeTurma, String tipo, String matricula) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
        this.nomeTurma = nomeTurma;
        this.tipo = tipo;
        this.matricula = matricula;
    }

    public void setHumorAtual(String humorAtual) {
        this.humorAtual = humorAtual;
    }

    private ArrayList<Humor> humores = new ArrayList<>();

    public Aluno() {}
    public Aluno(String nome, String email, String matricula){
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
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

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }
    public void setNomeTurma(String nomeTurma) { this.nomeTurma = nomeTurma; }

    public ArrayList<Humor> getHumores() { return humores; }
    public String getHumorAtual(){
        if(this.humores != null && !this.humores.isEmpty()){
            return humores.get(humores.size()-1).getNomeHumor().toString();
        }
        return "Sem descrição";
    }
    public void setHumores(ArrayList<Humor> humores) {
        this.humores = humores;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
