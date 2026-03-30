package service;

import model.Aluno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.AlunoRepository;

import java.util.List;

public class AlunoService {

    private static final Logger log = LoggerFactory.getLogger(AlunoService.class);

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public String cadastrar(String nome, String cpf) {
        log.info("Tentativa de cadastro - nome: {}, cpf: {}", nome, cpf);

        if (nome == null || nome.isBlank()) {
            log.warn("Cadastro rejeitado - nome vazio");
            return "ERRO: Nome não pode ser vazio!";
        }
        if (!nome.matches("[a-zA-ZÀ-ÿ\\s]+")) {
            log.warn("Cadastro rejeitado - nome com caracteres inválidos: {}", nome);
            return "ERRO: Nome não pode conter números ou caracteres especiais!";
        }
        if (cpf == null || cpf.isBlank()) {
            log.warn("Cadastro rejeitado - CPF vazio");
            return "ERRO: CPF não pode ser vazio!";
        }
        if (!cpf.matches("\\d+")) {
            log.warn("Cadastro rejeitado - CPF com letras: {}", cpf);
            return "ERRO: CPF deve conter apenas números!";
        }
        if (cpf.length() != 11) {
            log.warn("Cadastro rejeitado - CPF com tamanho inválido: {}", cpf);
            return "ERRO: CPF deve ter exatamente 11 dígitos!";
        }

        try {
            repository.salvar(new Aluno(nome.trim(), cpf.trim()));
            log.info("Aluno cadastrado com sucesso - nome: {}, cpf: {}", nome, cpf);
            return "Aluno cadastrado com sucesso! Nome: " + nome;
        } catch (RuntimeException e) {
            log.error("Erro ao cadastrar aluno - {}", e.getMessage());
            return "ERRO: " + e.getMessage();
        }
    }

    // Mantém compatibilidade com o controller
    public String cadastrarAluno(String nome, String cpf) {
        return cadastrar(nome, cpf);
    }

    public List<Aluno> listarTodos() {
        log.info("Listando todos os alunos");
        return repository.listarTodos();
    }

    // Mantém compatibilidade com o controller
    public List<Aluno> listarAlunos() {
        return listarTodos();
    }

    public String deletarTodos(boolean confirmado) {
        if (!confirmado) {
            log.info("Deleção cancelada pelo usuário");
            return "Operação cancelada.";
        }
        repository.deletarTodos();
        log.warn("TODOS os alunos foram deletados!");
        return "Todos os alunos foram removidos.";
    }

    public void deletarTodos() {
        repository.deletarTodos();
        log.warn("TODOS os alunos foram deletados!");
    }
}