package com.project.project_healtheducation.model;

import java.util.ArrayList;

public class Psicologo extends Usuario {
    private String idenficacaoProfissional;
    private ArrayList<Turma> turmasAcompanhadas = new ArrayList<>();

    public Psicologo(UserType tipo, String email, String name, int id, int age, String idenficacaoProfissional ) {
        super(tipo, email, name, id, age);
        this.idenficacaoProfissional = idenficacaoProfissional;

    }

    public Psicologo( String email, String name, int id, String password, String idenficacaoProfissional) {
        super( email, name, id, password);
        this.idenficacaoProfissional = idenficacaoProfissional;
    }

    public String getIdenficacaoProfissional() {
        return idenficacaoProfissional;
    }

    public ArrayList<Turma> getTurmasAcompanhadas() {
        return turmasAcompanhadas;
    }

    public void addTurma(Turma novaTurma) {
        this.turmasAcompanhadas.add(novaTurma);
    }

    public void rmvTurma(Turma turmaRemovida){
        this.turmasAcompanhadas.remove(turmaRemovida);
    }
}
