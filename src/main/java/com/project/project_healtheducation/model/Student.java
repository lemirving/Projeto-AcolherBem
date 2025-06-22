package com.project.project_healtheducation.model;

import com.project.project_healtheducation.manager.EmotionalStatusManager;
import com.project.project_healtheducation.manager.StudentPerformanceManager;

import java.util.ArrayList;
import java.util.List;

public class Student extends Usuario{
    private String anoEscolar;
    private String turma;
    private ArrayList<EmotionalStatusManager> registrosEmocionais = new ArrayList<>();
    private ArrayList<StudentPerformanceManager> desempenho = new ArrayList<>();

    public Student(UserType tipo, String email, String name, int id, int age, String anoEscolar, String turma, ArrayList<EmotionalStatusManager> registrosEmocionais, ArrayList<StudentPerformanceManager> desempenho) {
        super(tipo, email, name, id, age);
        this.anoEscolar = anoEscolar;
        this.turma = turma;
        this.registrosEmocionais = registrosEmocionais;
        this.desempenho = desempenho;
    }

    public Student(UserType tipo, String email, String name, int id, String password, int age, String anoEscolar, String turma, ArrayList<EmotionalStatusManager> registrosEmocionais, ArrayList<StudentPerformanceManager> desempenho) {
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

    public ArrayList<EmotionalStatusManager> getRegistrosEmocionais() {
        return registrosEmocionais;
    }

    public void setRegistrosEmocionais(ArrayList<EmotionalStatusManager> registrosEmocionais) {
        this.registrosEmocionais = registrosEmocionais;
    }

    public ArrayList<StudentPerformanceManager> getDesempenho() {
        return desempenho;
    }

    public void setDesempenho(ArrayList<StudentPerformanceManager> desempenho) {
        this.desempenho = desempenho;
    }

    public void addRegistroEmocional(EmotionalStatusManager registroNovo){
        this.registrosEmocionais.add(registroNovo);
    }
}
