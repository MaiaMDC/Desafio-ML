package com.notificaclima.notificacao_clima.xml;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
}
