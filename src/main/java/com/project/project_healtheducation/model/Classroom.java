package com.project.project_healtheducation.model;

import java.util.ArrayList;

public class Classroom {
    private String nomeTurma;//Pode aqui ser -> 2ºA, 8A e etc, não é um conjunto de números
    private int quantidade;
    private ArrayList<Student> alunosTurma = new ArrayList<>();

    public Classroom(String nomeTurma, int quantidade, ArrayList<Student> alunosTurma) {
        this.nomeTurma = nomeTurma;
        this.quantidade = quantidade;
        this.alunosTurma = alunosTurma;
    }
}
