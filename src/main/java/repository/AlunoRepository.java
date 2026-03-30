package repository;

import model.Aluno;
import java.util.List;

public interface AlunoRepository {
    void salvar(Aluno aluno);
    List<Aluno> listarTodos();
    void deletarTodos();
    boolean cpfJaCadastrado(String cpf);
    int proximoId();
}