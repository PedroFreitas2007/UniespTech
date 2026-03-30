package controller;

import model.Aluno;
import service.AlunoService;
import java.util.List;

public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    public String cadastrarAluno(String nome, String cpf) {
        return service.cadastrarAluno(nome, cpf);
    }

    public List<Aluno> listarAlunos() {
        return service.listarAlunos();
    }

    public String deletarTodos(boolean confirmado) {
        return service.deletarTodos(confirmado);
    }
}