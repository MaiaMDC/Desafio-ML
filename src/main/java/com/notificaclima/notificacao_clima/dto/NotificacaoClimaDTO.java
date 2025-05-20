package com.notificaclima.notificacao_clima.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.notificaclima.notificacao_clima.xml.PrevisaoOndas;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificacaoClimaDTO {
    private String usuario;
    private String cidade;
    private String uf;
    private List<PrevisaoDTO> previsoes;
    private PrevisaoOndas ondas;

    public NotificacaoClimaDTO() {
    }

    public NotificacaoClimaDTO(String usuario, String cidade, String uf, List<PrevisaoDTO> previsoes, PrevisaoOndas ondas) {
        this.usuario = usuario;
        this.cidade = cidade;
        this.uf = uf;
        this.previsoes = previsoes;
        this.ondas = ondas;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public List<PrevisaoDTO> getPrevisoes() {
        return previsoes;
    }

    public void setPrevisoes(List<PrevisaoDTO> previsoes) {
        this.previsoes = previsoes;
    }

    public PrevisaoOndas getOndas() {
        return ondas;
    }

    public void setOndas(PrevisaoOndas ondas) {
        this.ondas = ondas;
    }
}

