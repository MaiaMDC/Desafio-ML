package com.notificaclima.notificacao_clima.cptec.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;

import java.util.List;


@XmlRootElement(name = "cidade")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrevisaoCidade {

    @XmlElement(name = "nome")
    private String nome;

    @XmlElement(name = "uf")
    private String uf;

    @XmlElement(name = "atualizacao")
    private String atualizacao;

    @XmlElement(name = "previsao")
    private List<PrevisaoDia> previsoes;

    public PrevisaoCidade() {
    }

    public PrevisaoCidade(String nome, String uf, String atualizacao, List<PrevisaoDia> previsoes) {
        this.nome = nome;
        this.uf = uf;
        this.atualizacao = atualizacao;
        this.previsoes = previsoes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getAtualizacao() {
        return atualizacao;
    }

    public void setAtualizacao(String atualizacao) {
        this.atualizacao = atualizacao;
    }

    public List<PrevisaoDia> getPrevisoes() {
        return previsoes;
    }

    public void setPrevisoes(List<PrevisaoDia> previsoes) {
        this.previsoes = previsoes;
    }
}
