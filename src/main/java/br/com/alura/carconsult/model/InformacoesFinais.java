package br.com.alura.carconsult.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InformacoesFinais(@JsonAlias("Marca") String marca,
                                @JsonAlias("Modelo") String modelo,
                                @JsonAlias("AnoModelo") Integer anoModelo,
                                @JsonAlias("Combustivel") String combustivel,
                                @JsonAlias("MesReferencia") String mesReferencia,
                                @JsonAlias("Valor") String valor) {

    @Override
    public String toString() {
        return "\nMarca: " + this.marca + "\nModelo: " + this.modelo +
                "\nAno do Modelo: " + this.anoModelo + "\nCombustivel: " + this.combustivel +
                "\nMês De Referência: " + this.mesReferencia + "\nValor: " + this.valor;
    }
}
