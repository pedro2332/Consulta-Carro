package br.com.alura.carconsult.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListaModelos {
    @JsonAlias("modelos")
    List<Modelos> modelos;

    public List<Modelos> getModelos() {
        return modelos;
    }
}
