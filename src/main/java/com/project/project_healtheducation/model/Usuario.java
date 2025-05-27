package com.project.project_healtheducation.model;

public abstract class Usuario {
    private int id;
    private String name;
    private String email;
    private String password;
    private UserType type;//ALUNO, PROFESSOR, PSICOLOGO
    private int age;

    public Usuario(UserType tipo, String email, String name, int id, int age) {
        this.type = tipo;
        this.email = email;
        this.name = name;
        this.id = id;
        this.age = age;
    }
    public Usuario(String email, String name, int id, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public String getPassword(){
        return this.password;
    }
    public void setPassword(String newPassword){
        this.password = newPassword;
    }
}
