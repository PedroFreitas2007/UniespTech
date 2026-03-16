package model;

public class Aluno {

    private int    id;
    private String nome;
    private String cpf;

    public Aluno(String nome, String cpf) {
        this.nome = nome;
        this.cpf  = cpf;
    }

    public Aluno(int id, String nome, String cpf) {
        this.id   = id;
        this.nome = nome;
        this.cpf  = cpf;
    }

    public int    getId()   { return id; }
    public String getNome() { return nome; }
    public String getCpf()  { return cpf; }

    @Override
    public String toString() {
        return "Aluno{id=%d, nome='%s', cpf='%s'}".formatted(id, nome, cpf);
    }
}