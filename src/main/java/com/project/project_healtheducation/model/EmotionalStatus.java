package com.project.project_healtheducation.model;

import java.time.LocalDate;

public class EmotionalStatus {
    private LocalDate data;
    private String descricao;
    private int nivel;

    public EmotionalStatus(LocalDate data, String descricao, int nivel) {
        this.data = data;
        this.descricao = descricao;
        this.nivel = nivel;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getNivel() {
        return nivel;
    }
}
