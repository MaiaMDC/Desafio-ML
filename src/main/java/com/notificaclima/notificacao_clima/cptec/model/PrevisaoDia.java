package com.notificaclima.notificacao_clima.cptec.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;


@XmlAccessorType(XmlAccessType.FIELD)
public class PrevisaoDia {

    @XmlElement(name = "dia")
    private String dia;

    @XmlElement(name = "tempo")
    private String tempo;

    @XmlElement(name = "maxima")
    private int maxima;

    @XmlElement(name = "minima")
    private int minima;

    @XmlElement(name = "iuv")
    private double iuv;

    public PrevisaoDia() {
    }

    public PrevisaoDia(String dia, String tempo, int maxima, int minima, double iuv) {
        this.dia = dia;
        this.tempo = tempo;
        this.maxima = maxima;
        this.minima = minima;
        this.iuv = iuv;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public int getMaxima() {
        return maxima;
    }

    public void setMaxima(int maxima) {
        this.maxima = maxima;
    }

    public int getMinima() {
        return minima;
    }

    public void setMinima(int minima) {
        this.minima = minima;
    }

    public double getIuv() {
        return iuv;
    }

    public void setIuv(double iuv) {
        this.iuv = iuv;
    }
}
