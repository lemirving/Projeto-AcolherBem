package com.project.project_healtheducation.model;

import java.util.ArrayList;
import java.util.List;

public class Student extends User{
    private String anoEscolar;
    private String turma;
    private ArrayList<EmotionalStatus> registrosEmocionais = new ArrayList<>();
    private ArrayList<StudentPerformance> desempenho = new ArrayList<>();

    public Student(UserType tipo, String email, String name, String id, int age, String anoEscolar, String turma, ArrayList<EmotionalStatus> registrosEmocionais, ArrayList<StudentPerformance> desempenho) {
        super(tipo, email, name, id, age);
        this.anoEscolar = anoEscolar;
        this.turma = turma;
        this.registrosEmocionais = registrosEmocionais;
        this.desempenho = desempenho;
    }

    public Student(UserType tipo, String email, String name, String id, String password, int age, String anoEscolar, String turma, ArrayList<EmotionalStatus> registrosEmocionais, ArrayList<StudentPerformance> desempenho) {
        super(tipo, email, name, id, password, age);
        this.anoEscolar = anoEscolar;
        this.turma = turma;
        this.registrosEmocionais = registrosEmocionais;
        this.desempenho = desempenho;
    }

    public String getAnoEscolar() {
        return anoEscolar;
    }

    public String getTurma() {
        return turma;
    }

    public ArrayList<EmotionalStatus> getRegistrosEmocionais() {
        return registrosEmocionais;
    }

    public void setRegistrosEmocionais(ArrayList<EmotionalStatus> registrosEmocionais) {
        this.registrosEmocionais = registrosEmocionais;
    }

    public ArrayList<StudentPerformance> getDesempenho() {
        return desempenho;
    }

    public void setDesempenho(ArrayList<StudentPerformance> desempenho) {
        this.desempenho = desempenho;
    }

    public void addRegistroEmocional(EmotionalStatus registroNovo){
        this.registrosEmocionais.add(registroNovo);
    }
}
