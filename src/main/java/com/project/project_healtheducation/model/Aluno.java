package com.project.project_healtheducation.model;

import java.util.ArrayList;

public class Aluno {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private int idade;
    private String anoEscolar;
    private String nomeTurma;
    private String tipo;

    private ArrayList<StatusEmocional> registrosEmocionais = new ArrayList<>();

    public Aluno() {}

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
    public void setAnoEscolar(String anoEscolar) { this.anoEscolar = anoEscolar; }

    public String getNomeTurma() { return nomeTurma; }
    public void setNomeTurma(String nomeTurma) { this.nomeTurma = nomeTurma; }

    public ArrayList<StatusEmocional> getRegistrosEmocionais() { return registrosEmocionais; }
    public void setRegistrosEmocionais(ArrayList<StatusEmocional> registrosEmocionais) {
        this.registrosEmocionais = registrosEmocionais;
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
