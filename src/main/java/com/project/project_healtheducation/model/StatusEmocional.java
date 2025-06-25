package com.project.project_healtheducation.model;

import java.time.LocalDate;

public class StatusEmocional {
    private String nomeHumor;
    private int id;
    private int idAluno; //
    private LocalDate data;
    private String descricao;

    public StatusEmocional(String nomeHumor,int idAluno, LocalDate data, String descricao) {
        this.nomeHumor = nomeHumor;
        this.idAluno = idAluno;
        this.data = data;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StatusEmocional(String nomeHumor,int id, int idAluno, LocalDate data, String descricao, int nivel) {
        this.nomeHumor = nomeHumor;
        this.id = id;
        this.idAluno = idAluno;
        this.data = data;
        this.descricao = descricao;
//        this.nivel = nivel;
    }

    public String getNomeHumor() {
        return nomeHumor;
    }

    public void setNomeHumor(String nomeHumor) {
        this.nomeHumor = nomeHumor;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

//    public int getNivel() {
//        return nivel;
//    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

//    public void setNivel(int nivel) {
//        this.nivel = nivel;
//    }
}
