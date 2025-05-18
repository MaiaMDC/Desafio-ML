package com.notificaclima.notificacao_clima.xml;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
}
