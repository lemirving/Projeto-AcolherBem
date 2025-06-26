package com.project.project_healtheducation.model;

public interface Usuario {
    int getId();
    String getNome();
    String getEmail();

    String getIdade();

    void setNome(String nome);

    void setEmail(String email);

    void setIdade(String idade);
}
