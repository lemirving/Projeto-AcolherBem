package com.project.project_healtheducation.model;

import java.time.LocalDate;

public class Humor {
    private int id;
    private int idAluno; //
    private LocalDate data;
    private String nomeHumor;
    private int nivel;

    public Humor(int idAluno, LocalDate data, String humor, int nivel) {
        this.idAluno = idAluno;
        this.data = data;
        this.nomeHumor = humor;
        this.nivel = nivel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Humor(int id, int idAluno, LocalDate data, String descricao, int nivel) {
        this.id = id;
        this.idAluno = idAluno;
        this.data = data;
        this.nomeHumor = descricao;
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

    public String getNomeHumor() {
        return nomeHumor;
    }

    public void setNomeHumor(String nomeHumor) {
        this.nomeHumor = nomeHumor;
    }

    public int getNivel() {
        return nivel;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
