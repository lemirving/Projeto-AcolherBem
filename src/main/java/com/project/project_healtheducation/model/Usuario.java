package com.project.project_healtheducation.model;

public interface Usuario {

    int getId();
    void setId(int id);

    String getNome();
    void setNome(String nome);

    String getEmail();
    void setEmail(String email);

    String getSenha();
    void setSenha(String senha);

    String getTipo();
    void setTipo(String tipo);

    String getCaminhoImagem();
    void setCaminhoImagem(String caminhoImagemm);

}