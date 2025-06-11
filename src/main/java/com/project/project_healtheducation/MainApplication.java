package com.project.project_healtheducation;

import com.project.project_healtheducation.dao.*;
import com.project.project_healtheducation.db.dbSetup;
import com.project.project_healtheducation.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Teste");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Aluno aluno = new Aluno("Irving", "2B", "9ano", 15, "12345", "irving@gmail.com", new ArrayList<>());
        AlunoDAO dao = new AlunoDAO();
        dao.inserirAluno(aluno);

        Professor prof = new Professor("Jose","jose@gmail","12345",40,"Ed. Física",new ArrayList<>());
        ProfessorDAO daoProf = new ProfessorDAO();
        daoProf.inserirProfessor(prof);

        Psicologo psicologo = new Psicologo("Maria","maria@gmail","1234",28,"7662uva",new ArrayList<>());
        PsicologoDAO daoPsico = new PsicologoDAO();
        daoPsico.inserirPsicologo(psicologo);

        Turma turma = new Turma("9C",
                30);
        TurmaDAO daoTurma = new TurmaDAO();
        daoTurma.inserirTurma(turma);
         //Associar psicólogo à turma
        daoPsico.associarTurmaAoPsicologo(psicologo.getId(), turma.getNomeTurma());

         //Associar professor à turma
        daoProf.associarTurmaAoProfessor(prof.getId(), turma.getNomeTurma());

        StatusEmocionalDAO daoStatus = new StatusEmocionalDAO();

        StatusEmocional status = new StatusEmocional(aluno.getId(), LocalDate.now(), "Ansioso", 4);
        daoStatus.inserirEmocao(status);




        dbSetup.createTables();
        launch();
    }
}