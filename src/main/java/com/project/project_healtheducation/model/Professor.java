package com.project.project_healtheducation.model;

import java.util.ArrayList;

public class Professor implements Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;

    private String tipo;
    private String caminhoImagem;

    // REMOVIDO: private String descricaoProfessor; // Se não estiver em uso ou no banco para Professor

    // Construtor vazio (essencial para DAOs que usam setters)
    public Professor() {}

    // Construtor para criação/busca de Professor com dados do banco
    // ATENÇÃO: Assegure-se de que a ordem e o tipo dos parâmetros correspondem ao seu DAO.
    public Professor(int id, String nome, String email, String senha, String tipo, String caminhoImagem) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.caminhoImagem = caminhoImagem;
    }

    // Construtor para inserção (sem ID, pois o banco gera)
    public Professor(String nome, String email, String senha, String tipo, String caminhoImagem) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.caminhoImagem = caminhoImagem;
    }


    // --- Getters e Setters ---
    @Override // Marque como @Override se estiver implementando da interface Usuario
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Override
    public String getNome() { return nome; }
    @Override
    public void setNome(String nome) { this.nome = nome; }

    @Override
    public String getEmail() { return email; }
    @Override
    public void setEmail(String email) { this.email = email; }

    @Override
    public String getSenha() { return senha; }
    @Override
    public void setSenha(String senha) { this.senha = senha; }

    // REMOVIDO: getIdade() e setIdade() - Não são mais de Professor
    // REMOVIDO: getEspecialidade() e setEspecialidade() - Não são mais de Professor

    @Override
    public String getTipo() { return tipo; }
    @Override
    public void setTipo(String tipo) { this.tipo = tipo; }


    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    // Se turmasLecionadas for um atributo relevante para o modelo de Professor
    // e não apenas para o DAO, mantenha. Caso contrário, pode ser movido apenas para o DAO.
    private ArrayList<Turma> turmasLecionadas = new ArrayList<>(); // Inicialize aqui para evitar NullPointerException
    public ArrayList<Turma> getTurmasLecionadas() { return turmasLecionadas; }
    public void setTurmasLecionadas(ArrayList<Turma> turmasLecionadas) { this.turmasLecionadas = turmasLecionadas; }

    public void adicionarTurma(Turma novaTurma) {
        if (this.turmasLecionadas == null) { // Garantia contra null se não inicializado no construtor
            this.turmasLecionadas = new ArrayList<>();
        }
        this.turmasLecionadas.add(novaTurma);
    }

    public void removerTurma(Turma turmaRemovida) {
        if (this.turmasLecionadas != null) {
            this.turmasLecionadas.remove(turmaRemovida);
        }
    }

    // Se 'descricaoProfessor' não estiver no banco ou em uso, remova.
    // public String getDescricaoProfessor() { return descricaoProfessor; }
    // public void setDescricaoProfessor(String descricaoProfessor) { this.descricaoProfessor = descricaoProfessor; }
}