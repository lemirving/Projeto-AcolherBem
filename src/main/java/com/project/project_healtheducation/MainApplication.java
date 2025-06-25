package com.project.project_healtheducation;

import com.project.project_healtheducation.dao.AlunoDAO;
import com.project.project_healtheducation.db.dbSetup;
import com.project.project_healtheducation.model.Aluno;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/com/project/project_healtheducation/view/paginaInicial" +
                ".fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1220, 800);
        stage.setTitle("PsicoEduca");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        Aluno aluno = new Aluno("Irving","irving@gmail.com","123456","21","2B","Aluno","100200222");
//        AlunoDAO daoAluno = new AlunoDAO();
//        daoAluno.inserirAluno(aluno);
//        Aluno aluno1 = new Aluno("Maria Silva", "maria.silva@gmail.com", "senha1", "22", "3A", "Aluno", "100200223");
//        Aluno aluno2 = new Aluno("João Costa", "joao.costa@gmail.com", "senha2", "20", "2B", "Aluno", "100200224");
//        Aluno aluno3 = new Aluno("Ana Pereira", "ana.pereira@gmail.com", "senha3", "23", "1C", "Aluno", "100200225");
//        Aluno aluno4 = new Aluno("Carlos Eduardo", "carlos.eduardo@gmail.com", "senha4", "21", "3B", "Aluno", "100200226");
//        Aluno aluno5 = new Aluno("Beatriz Souza", "beatriz.souza@gmail.com", "senha5", "22", "2A", "Aluno", "100200227");
//        Aluno aluno6 = new Aluno("Rafael Lima", "rafael.lima@gmail.com", "senha6", "24", "1B", "Aluno", "100200228");
//        Aluno aluno7 = new Aluno("Fernanda Alves", "fernanda.alves@gmail.com", "senha7", "20", "3C", "Aluno", "100200229");
//        Aluno aluno8 = new Aluno("Lucas Martins", "lucas.martins@gmail.com", "senha8", "23", "2C", "Aluno", "100200230");
//        Aluno aluno9 = new Aluno("Patrícia Gomes", "patricia.gomes@gmail.com", "senha9", "21", "1A", "Aluno", "100200231");
//        Aluno aluno10 = new Aluno("Eduardo Rocha", "eduardo.rocha@gmail.com", "senha10", "22", "3A", "Aluno", "100200232");
//        Aluno aluno11 = new Aluno("Monique Lara", "moniquinhaLara@gmail.com", "senha11", "24", "9A", "Aluno", "100200232");
//        Aluno aluno12 = new Aluno("João Henrique", "joaohenrique@gmail.com", "senha12", "24", "9A", "Aluno", "100200232");


//        daoAluno.inserirAluno(aluno1);
//        daoAluno.inserirAluno(aluno2);
//        daoAluno.inserirAluno(aluno3);
//        daoAluno.inserirAluno(aluno4);
//        daoAluno.inserirAluno(aluno5);
//        daoAluno.inserirAluno(aluno6);
//        daoAluno.inserirAluno(aluno7);
//        daoAluno.inserirAluno(aluno8);
//        daoAluno.inserirAluno(aluno9);
//        daoAluno.inserirAluno(aluno10);
//        daoAluno.inserirAluno(aluno11);
//        daoAluno.inserirAluno(aluno12);



        dbSetup.createTables();
        launch();
    }
}