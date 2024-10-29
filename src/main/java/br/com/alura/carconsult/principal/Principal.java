package br.com.alura.carconsult.principal;


import br.com.alura.carconsult.model.*;
import br.com.alura.carconsult.services.BuscaTodosAnos;
import br.com.alura.carconsult.services.ConsumeApi;
import br.com.alura.carconsult.services.ConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Principal {
    private ConsumeApi consumeApi = new ConsumeApi();
    private Scanner scanner = new Scanner(System.in);
    private String endereco = "https://parallelum.com.br/fipe/api/v1/carros/marcas";
    private ConverteDados conversor = new ConverteDados();
    private ObjectMapper mapper = new ObjectMapper();
    List<DadosMarca> listaDadosMarca;
    ListaModelos listaModelos;
    List<Anos> anos;
    InformacoesFinais informacoesFinais;
    BuscaTodosAnos buscaTodosAnos = new BuscaTodosAnos();
    Map<String, Integer> dadosMarca = new HashMap<>();




    public void exibeMenu(){
        var json = consumeApi.consumirApi(endereco);
        //System.out.println(json);
        try {
            listaDadosMarca = mapper.readValue(json, new TypeReference<List<DadosMarca>>() {
            });

            for (DadosMarca marca : listaDadosMarca) {
                dadosMarca.put(marca.getNome().toUpperCase(), Integer.parseInt(marca.getCodigo()));
            }
        }catch (Exception e) {
            throw new RuntimeException();
        }

        listaDadosMarca.forEach(marca -> System.out.println(marca.getNome() + "\n"));
        System.out.println("\nDigite o nome da marca que deseja buscar os modelos, de maneira exatamente igual à lista fornecida acima:");
        var marcaEscolhida = scanner.nextLine().toUpperCase();
        System.out.println("Modelos para marca " + marcaEscolhida + "\n");
        var codigoEscolhido = dadosMarca.get(marcaEscolhida);
        //System.out.println(endereco + "/" + codigoEscolhido);
        json = consumeApi.consumirApi((endereco + "/" + codigoEscolhido + "/modelos"));
        try {
            listaModelos = mapper.readValue(json, ListaModelos.class);
            //System.out.println(listaModelos.getModelos().get(2));
        } catch (Exception e) {
            throw new RuntimeException();
        }
        System.out.println(listaModelos.getModelos());
        System.out.println("\nBaseado na lista acima, digite a seguir o código do modelo que deseja, " +
                "para poder escolher o ano!");
        var codigoModelo = scanner.nextLine();
        System.out.println("Anos para o modelo escolhido: ");
        json = consumeApi.consumirApi((endereco + "/" + codigoEscolhido +
                "/modelos" + "/" + codigoModelo + "/anos"));
//        System.out.println(endereco + "/" + codigoEscolhido +
//                "/modelos" + "/" + codigoModelo + "/anos");
        try {
            anos = mapper.readValue(json, new TypeReference<List<Anos>>() {});
            System.out.println(anos);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\nDigite o código do ano do modelo que " +
                "deseja visualizar os dados (Da mesma maneira escrita" +
                " na lista, ex: '2020-1'");
        var codigoAno = scanner.nextLine();
        System.out.println("\nDados para o veículo da marca " + marcaEscolhida);
        json = consumeApi.consumirApi((endereco + "/" + codigoEscolhido +
                "/modelos/" + codigoModelo + "/anos/" + codigoAno));;
        try {
            informacoesFinais = mapper.readValue(json, InformacoesFinais.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(informacoesFinais);

        System.out.println("""
                \nVocê deseja visualizar a tabela de todos os anos deste modelo de carro?
                1- Sim
                2- Não
                Digite sua resposta: """);
        var botaoPrecoTodosAnos = scanner.nextInt();
        scanner.nextLine();
        switch(botaoPrecoTodosAnos){
            case 1:
                buscaTodosAnos.buscarTodosAnos(consumeApi.consumirApi((endereco + "/" + codigoEscolhido +
                        "/modelos/" + codigoModelo + "/anos/")));
        }

    }
}
