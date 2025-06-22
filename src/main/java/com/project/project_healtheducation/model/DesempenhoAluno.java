package com.project.project_healtheducation.model;

import java.time.LocalDate;

public class DesempenhoAluno {

    private String materia;
    private String periodoAtual;
    private double nota;
    private String observacoes;
    private LocalDate data;

    public DesempenhoAluno(String materia, double nota, String observacoes, LocalDate data, String periodoAtual) {
        this.materia = materia;
        this.nota = nota;
        this.observacoes = observacoes;
        this.data = data;
        this.periodoAtual = periodoAtual;
    }

    public String getMateria() {return materia;}

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
