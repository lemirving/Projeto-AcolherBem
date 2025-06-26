package com.project.project_healtheducation.model;

import java.time.LocalDate;

public class
Humor {
    private int id;
    private int idAluno;
    private LocalDate data;
    private String descricao;
    private String nomeHumor;

    // Construtor completo
    public Humor(int id, String nomeHumor, int idAluno, LocalDate data, String descricao) {
        this.id = id;
        this.nomeHumor = nomeHumor;
        this.idAluno = idAluno;
        this.data = data;
        this.descricao = descricao;
    }

    // Construtor sem ID (para inserção no banco)
    public Humor(String nomeHumor, int idAluno, LocalDate data, String descricao) {
        this.nomeHumor = nomeHumor;
        this.idAluno = idAluno;
        this.data = data;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNomeHumor() {
        return nomeHumor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setNomeHumor(String nomeHumor) {
        this.nomeHumor = nomeHumor;
    }
}
