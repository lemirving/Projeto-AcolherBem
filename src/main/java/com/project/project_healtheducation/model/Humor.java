package com.project.project_healtheducation.model;

import java.time.LocalDate;

public class StatusEmocional {
    private int id;
    private int idAluno; //
    private LocalDate data;
    private String emocao;
    private int nivel;

    public StatusEmocional(int idAluno, LocalDate data, String descricao, int nivel) {
        this.idAluno = idAluno;
        this.data = data;
        this.emocao = descricao;
        this.nivel = nivel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StatusEmocional(int id, int idAluno, LocalDate data, String descricao, int nivel) {
        this.id = id;
        this.idAluno = idAluno;
        this.data = data;
        this.emocao = descricao;
        this.nivel = nivel;
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

    public String getEmocao() {
        return emocao;
    }

    public int getNivel() {
        return nivel;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setEmocao(String emocao) {
        this.emocao = emocao;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
