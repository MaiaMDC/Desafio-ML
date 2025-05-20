package com.notificaclima.notificacao_clima.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class PeriodoOndas {

    @XmlElement(name = "dia")
    private String dia;
    @XmlElement(name = "agitacao")
    private String agitacao;
    @XmlElement(name = "altura")
    private double altura;
    @XmlElement(name = "direcao")
    private String direcao;
    @XmlElement(name = "vento")
    private double vento;
    @XmlElement(name = "vento_dir")
    private String ventoDir;

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getAgitacao() {
        return agitacao;
    }

    public void setAgitacao(String agitacao) {
        this.agitacao = agitacao;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getDirecao() {
        return direcao;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }

    public double getVento() {
        return vento;
    }

    public void setVento(double vento) {
        this.vento = vento;
    }

    public String getVentoDir() {
        return ventoDir;
    }

    public void setVentoDir(String ventoDir) {
        this.ventoDir = ventoDir;
    }
}

