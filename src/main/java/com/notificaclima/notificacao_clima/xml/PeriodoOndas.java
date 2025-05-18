package com.notificaclima.notificacao_clima.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
}

