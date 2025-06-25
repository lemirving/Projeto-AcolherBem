package com.project.project_healtheducation.utils;

import com.project.project_healtheducation.model.Aluno;
import com.project.project_healtheducation.model.Usuario;

// CLASSE RESPONSAVEL PRA VER SE O USUARIO TÁ LOGADO
public class SessaoUsuario {
    private static Usuario usuarioLogado;

    public static void setUsuarioLogado(Usuario usuario) {
        SessaoUsuario.usuarioLogado = usuario;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void limparSessao() {
        usuarioLogado = null;
    }
}
