package com.project.project_healtheducation.model;

import java.util.ArrayList;

public class Aluno implements Usuario{
    private int id;
    private String nome;
    private String email;
    private String senha;
    private int idade;
    private String tipo;
    private String anoEscolar;
    private String nomeTurma;

    private ArrayList<StatusEmocional> registrosEmocionais = new ArrayList<>();

    public Aluno() {}

    public Aluno( String nome, int id,String nomeTurma,
                 String anoEscolar, int idade, String senha, String email,ArrayList<StatusEmocional> registrosEmocionais) {
        this.registrosEmocionais = registrosEmocionais;
        this.nomeTurma = nomeTurma;
        this.anoEscolar = anoEscolar;
        this.idade = idade;
        this.senha = senha;
        this.email = email;
        this.nome = nome;
        this.id = id;
    }
    public Aluno( String nome,String nomeTurma,
                  String anoEscolar, int idade, String senha, String email,ArrayList<StatusEmocional> registrosEmocionais) {
        this.registrosEmocionais = registrosEmocionais;
        this.nomeTurma = nomeTurma;
        this.anoEscolar = anoEscolar;
        this.idade = idade;
        this.senha = senha;
        this.email = email;
        this.nome = nome;
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

    public String getAnoEscolar() { return anoEscolar; }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public void setAnoEscolar(String anoEscolar) { this.anoEscolar = anoEscolar;}

    public String setTipo(String tipo) {
        return this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
