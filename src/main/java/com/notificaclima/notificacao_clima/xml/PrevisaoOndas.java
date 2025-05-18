package com.notificaclima.notificacao_clima.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
}

