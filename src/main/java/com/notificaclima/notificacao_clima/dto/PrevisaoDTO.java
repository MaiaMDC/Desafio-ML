package com.notificaclima.notificacao_clima.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrevisaoDTO {
    private String data;
    private int minima;
    private int maxima;
    private String tempo;

    public PrevisaoDTO() {
    }

    public PrevisaoDTO(String data, int minima, int maxima, String tempo) {
        this.data = data;
        this.minima = minima;
        this.maxima = maxima;
        this.tempo = tempo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getMinima() {
        return minima;
    }

    public void setMinima(int minima) {
        this.minima = minima;
    }

    public int getMaxima() {
        return maxima;
    }

    public void setMaxima(int maxima) {
        this.maxima = maxima;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }
}

