package com.project.project_healtheducation.model;

import java.util.ArrayList;

public class Turma {
    private String nomeTurma;//Pode aqui ser -> 2ºA, 8A e etc, não é um conjunto de números
    private int quantidade;
    private ArrayList<Aluno> alunosTurma = new ArrayList<>();

    public Turma(String nomeTurma, int quantidade, ArrayList<Aluno> alunosTurma) {
        this.nomeTurma = nomeTurma;
        this.quantidade = quantidade;
        this.alunosTurma = alunosTurma;
    }
    public Turma(String nomeTurma, int quantidade) {
        this.nomeTurma = nomeTurma;
        this.quantidade = quantidade;
        this.alunosTurma = new ArrayList<>();
    }


    public String getNomeTurma() {
        return nomeTurma;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public ArrayList<Aluno> getAlunosTurma() {
        return alunosTurma;
    }
}
