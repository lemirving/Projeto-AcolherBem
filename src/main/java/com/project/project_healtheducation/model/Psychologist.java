package com.project.project_healtheducation.model;

import java.util.ArrayList;

public class Psychologist extends User{
    private String idenficacaoProfissional;
    private ArrayList<Classroom> turmasAcompanhadas = new ArrayList<>();

    public Psychologist(UserType tipo, String email, String name, String id, int age, String idenficacaoProfissional ) {
        super(tipo, email, name, id, age);
        this.idenficacaoProfissional = idenficacaoProfissional;

    }

    public Psychologist(UserType tipo, String email, String name, String id, String password, int age, String idenficacaoProfissional) {
        super(tipo, email, name, id, password, age);
        this.idenficacaoProfissional = idenficacaoProfissional;
    }

    public String getIdenficacaoProfissional() {
        return idenficacaoProfissional;
    }

    public ArrayList<Classroom> getTurmasAcompanhadas() {
        return turmasAcompanhadas;
    }

    public void addTurma(Classroom novaTurma) {
        this.turmasAcompanhadas.add(novaTurma);
    }

    public void rmvTurma(Classroom turmaRemovida){
        this.turmasAcompanhadas.remove(turmaRemovida);
    }
}
