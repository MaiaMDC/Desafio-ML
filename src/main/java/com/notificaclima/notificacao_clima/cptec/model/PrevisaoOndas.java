package com.notificaclima.notificacao_clima.cptec.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "cidade")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrevisaoOndas {

    @XmlElement(name = "nome")
    private String nome;
    @XmlElement(name = "uf")
    private String uf;
    @XmlElement(name = "atualizacao")
    private String atualizacao;
    @XmlElement(name = "manha")
    private PeriodoOndas manha;
    @XmlElement(name = "tarde")
    private PeriodoOndas tarde;
    @XmlElement(name = "noite")
    private PeriodoOndas noite;

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

    public PeriodoOndas getManha() {
        return manha;
    }

    public void setManha(PeriodoOndas manha) {
        this.manha = manha;
    }

    public PeriodoOndas getTarde() {
        return tarde;
    }

    public void setTarde(PeriodoOndas tarde) {
        this.tarde = tarde;
    }

    public PeriodoOndas getNoite() {
        return noite;
    }

    public void setNoite(PeriodoOndas noite) {
        this.noite = noite;
    }
}

