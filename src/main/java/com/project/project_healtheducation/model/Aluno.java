package com.project.project_healtheducation.model;

import java.util.ArrayList;

public class Aluno extends Usuario {
    private String anoEscolar;
    private String turma;
    private ArrayList<StatusEmocional> registrosEmocionais = new ArrayList<>();
    private ArrayList<DesempenhoAluno> desempenho = new ArrayList<>();

    public Aluno(UserType tipo, String email, String name, int id, int age, String anoEscolar, String turma, ArrayList<StatusEmocional> registrosEmocionais, ArrayList<DesempenhoAluno> desempenho) {
        super(tipo, email, name, id, age);
        this.anoEscolar = anoEscolar;
        this.turma = turma;
        this.registrosEmocionais = registrosEmocionais;
        this.desempenho = desempenho;
    }

    public Aluno(String email, String name, int id, String password, String anoEscolar, String turma, ArrayList<StatusEmocional> registrosEmocionais, ArrayList<DesempenhoAluno> desempenho) {
        super(email, name, id, password);
        this.anoEscolar = anoEscolar;
        this.turma = turma;
        this.registrosEmocionais = registrosEmocionais;
        this.desempenho = desempenho;
    }

    public Aluno(String email, String name, int id, String password, String anoEscolar, String turma) {
        super(email,name,id,password);
        this.anoEscolar = anoEscolar;
        this.turma = turma;
    }

    public String getAnoEscolar() {
        return anoEscolar;
    }

    public void setAnoEscolar(String anoEscolar) {
        this.anoEscolar = anoEscolar;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public ArrayList<StatusEmocional> getRegistrosEmocionais() {
        return registrosEmocionais;
    }

    public void setRegistrosEmocionais(ArrayList<StatusEmocional> registrosEmocionais) {
        this.registrosEmocionais = registrosEmocionais;
    }

    public ArrayList<DesempenhoAluno> getDesempenho() {
        return desempenho;
    }

    public void setDesempenho(ArrayList<DesempenhoAluno> desempenho) {
        this.desempenho = desempenho;
    }

    public void addRegistroEmocional(StatusEmocional registroNovo){
        this.registrosEmocionais.add(registroNovo);
    }
}
