package br.com.alura.carconsult.services;

public interface IConverteDados {
    <T> T converterDados(String json, Class<T> classe);
}
