package br.com.alura.carconsult.services;

import br.com.alura.carconsult.model.Anos;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class BuscaTodosAnos extends ConsumeApi{
    private List<Anos> listaTodosAnos;
    private ObjectMapper mapper = new ObjectMapper();

    public void buscarTodosAnos (String enderecoApi) {

    }
}
