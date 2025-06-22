package com.project.project_healtheducation.model;

import java.util.ArrayList;

public class Professor extends Usuario {

    private String especialidade;
    private ArrayList<Turma> turmasLecionadas;

    public Professor(UserType tipo, String email, String name, int id, int age, String especialidade) {
        super(tipo, email, name, id, age);
        this.especialidade = especialidade;
    }


    public Professor(String email, String name, int id, String password, String especialidade, ArrayList<Turma> turmasLecionadas) {
        super(email, name, id, password);
        this.especialidade = especialidade;
        this.turmasLecionadas = turmasLecionadas;
    }



    public String getEspecialidade() {
        return especialidade;
    }

    public ArrayList<Turma> getTurmasLecionadas() {
        return turmasLecionadas;
    }

    public void addTurma(Turma novaTurma) {
        this.turmasLecionadas.add(novaTurma);
    }

    public void rmvTurma(Turma turmaRemovida){
        this.turmasLecionadas.remove(turmaRemovida);
    }
}
