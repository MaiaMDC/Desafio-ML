package com.notificaclima.notificacao_clima.services;

import com.notificaclima.notificacao_clima.xml.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class CptecClientService {

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Cidade> findCity(String nome) throws Exception {
        String url = "http://servicos.cptec.inpe.br/XML/listaCidades?city=" + URLEncoder.encode(nome, StandardCharsets.UTF_8);

        JAXBContext context = JAXBContext.newInstance(ListaCidades.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        try (InputStream input = new URL(url).openStream()) {
            ListaCidades listaCidades = (ListaCidades) unmarshaller.unmarshal(input);
            return listaCidades.getCidades();
        }
    }


    public PrevisaoCidade getPrevisaoByCityId(int cidadeId) throws Exception {
        String url = "http://servicos.cptec.inpe.br/XML/cidade/" + cidadeId + "/previsao.xml";

        URL apiUrl = new URL(url);
        JAXBContext jaxbContext = JAXBContext.newInstance(PrevisaoCidade.class, PrevisaoDia.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (PrevisaoCidade) unmarshaller.unmarshal(apiUrl);
    }

    public PrevisaoOndas getPrevisaoOndas(int cidadeId) throws Exception {
        String url = "http://servicos.cptec.inpe.br/XML/cidade/" + cidadeId + "/dia/0/ondas.xml";

        JAXBContext context = JAXBContext.newInstance(PrevisaoOndas.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        try (InputStream input = new URL(url).openStream()) {
            return (PrevisaoOndas) unmarshaller.unmarshal(input);
        }
    }
}
