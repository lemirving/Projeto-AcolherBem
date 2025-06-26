package com.project.project_healtheducation;

import com.project.project_healtheducation.controllers.TelaPerfilController; // Certifique-se de importar o controller correto
import com.project.project_healtheducation.dao.AlunoDAO;
import com.project.project_healtheducation.db.dbSetup;
import com.project.project_healtheducation.model.Aluno;
import com.project.project_healtheducation.utils.SessaoUsuario; // Importe SessaoUsuario
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
// import java.util.ArrayList; // Não usado no código atual, pode remover
// import com.project.project_healtheducation.dao.ProfessorDAO; // Não usado
// import com.project.project_healtheducation.dao.PsicologoDAO;  // Não usado
// import com.project.project_healtheducation.dao.StatusEmocionalDAO; // Não usado
// import com.project.project_healtheducation.dao.TurmaDAO; // Não usado
// import com.project.project_healtheducation.model.Professor; // Não usado
// import com.project.project_healtheducation.model.Psicologo; // Não usado
// import com.project.project_healtheducation.model.StatusEmocional; // Não usado
// import com.project.project_healtheducation.model.Turma; // Não usado
// import java.time.LocalDate; // Não usado

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Nada muda aqui, pois o setup do usuário será feito no main antes do launch()
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/com/project/project_healtheducation/view/professor/telaPerfil.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1220, 800);
        stage.setTitle("MentalHeathCare");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // Garante que as tabelas estejam criadas ANTES de tentar buscar ou inserir dados
        dbSetup.createTables();

        // 1. Inserir um aluno para garantir que haja um para testar
        // Se você já tem alunos no banco de dados, pode comentar essas linhas.
        // Se não, descomente e execute UMA VEZ para popular o DB.
        AlunoDAO daoAluno = new AlunoDAO();
        // Aluno alunoTeste = new Aluno("Aluno Teste", "aluno.teste@email.com", "senhaTeste", 25, "4A", "Aluno", null);
        // daoAluno.inserirAluno(alunoTeste);
        // System.out.println("Aluno de teste inserido com ID: " + alunoTeste.getId()); // Isso pode não mostrar o ID se o DAO não retornar.

        // 2. Busque um aluno existente do banco de dados pelo ID
        // *** IMPORTANTE: Mude '1' para o ID de um aluno que VOCÊ SABE que existe no seu banco de dados.
        // Você pode verificar isso usando um cliente de banco de dados (Ex: DBeaver, SQLiteBrowser).
        Aluno alunoParaTestar = daoAluno.buscarAlunoPorId(1); // Substitua '1' pelo ID do aluno que deseja testar

        if (alunoParaTestar != null) {
            System.out.println("Aluno encontrado para teste: " + alunoParaTestar.getNome() + " (ID: " + alunoParaTestar.getId() + ")");

            // 3. Define o aluno encontrado como o usuário logado na sessão
            // Isso é CRUCIAL para que a TelaPerfilController funcione a lógica de edição/salvamento.
            SessaoUsuario.setUsuarioLogado(alunoParaTestar);
            System.out.println("SessaoUsuario configurada para o aluno: " + SessaoUsuario.getUsuarioLogado().getNome());

        } else {
            System.err.println("ERRO: Nenhum aluno encontrado com o ID especificado. Verifique seu banco de dados e o ID informado.");
            // Opcional: Você pode optar por sair da aplicação ou mostrar uma mensagem de erro aqui
            // System.exit(1);
        }

        // Lança a aplicação JavaFX. O método 'start' será chamado.
        launch();
    }
}