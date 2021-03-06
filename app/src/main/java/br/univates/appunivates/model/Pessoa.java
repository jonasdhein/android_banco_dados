package br.univates.appunivates.model;

public class Pessoa {

    private int id;
    private String nome;
    private String telefone;
    private String data_nascimento;
    private String cpf;
    private int id_linguagem;

    //ATL + INSERT
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getId_linguagem() {
        return id_linguagem;
    }

    public void setId_linguagem(int id_linguagem) {
        this.id_linguagem = id_linguagem;
    }

    @Override
    public String toString(){
        return this.nome;
    }
}
