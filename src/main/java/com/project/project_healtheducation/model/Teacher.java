package com.project.project_healtheducation.model;

import java.util.ArrayList;

public class Teacher extends User{

    private String specialty;
    private ArrayList<Classroom> turmasLecionadas;

    public Teacher(UserType tipo, String email, String name, String id, int age, String specialty) {
        super(tipo, email, name, id, age);
        this.specialty = specialty;
    }

    public Teacher(UserType tipo, String email, String name, String id, String password, String specialty, int age) {
        super(tipo, email, name, id, password, age);
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public ArrayList<Classroom> getTurmasLecionadas() {
        return turmasLecionadas;
    }

    public void addTurma(Classroom novaTurma) {
        this.turmasLecionadas.add(novaTurma);
    }

    public void rmvTurma(Classroom turmaRemovida){
        this.turmasLecionadas.remove(turmaRemovida);
    }
}
