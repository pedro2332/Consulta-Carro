package br.com.alura.carconsult.model;

public class Anos {
    private String codigo;
    private String nome;


    public String getCodigo() {
        return codigo;
    }


    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "\nNome: " + this.nome + "\nCódigo: " + this.codigo;
    }
}
