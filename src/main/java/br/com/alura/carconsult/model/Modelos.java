package br.com.alura.carconsult.model;

public record Modelos(String codigo, String nome) {
    @Override
    public String toString() {
        return "\nModelo: " + this.nome + "\nCodigo: " + this.codigo;
    }
}
