package com.project.project_healtheducation.model;

public abstract class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private UserType type;//ALUNO, PROFESSOR, PSICOLOGO
    private int age;

    public User(UserType tipo, String email, String name, String id, int age) {
        this.type = tipo;
        this.email = email;
        this.name = name;
        this.id = id;
        this.age = age;
    }
    public User(UserType tipo, String email, String name, String id, String password, int age) {
        this.type = tipo;
        this.email = email;
        this.name = name;
        this.password = password;
        this.id = id;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public void setAge(int age) {
        this.age = age;
    }
}
