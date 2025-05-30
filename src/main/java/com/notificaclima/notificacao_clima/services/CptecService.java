package com.notificaclima.notificacao_clima.services;

import com.notificaclima.notificacao_clima.cptec.model.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import org.springframework.retry.annotation.Recover;

@Service
public class CptecService {

    @Value("${cptec.api.base-url}")
    private String baseUrl;

    @Value("${cptec.api.previsao-url}")
    private String previsaoUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    private static final Logger log = LoggerFactory.getLogger(CptecService.class);

    @Retryable(value = { Exception.class }, maxAttempts = 3, backoff = @Backoff(delay = 5000))
    public List<Cidade> buscaCidade(String nome) throws Exception {
        String url = baseUrl + URLEncoder.encode(nome, StandardCharsets.UTF_8);

        JAXBContext context = JAXBContext.newInstance(ListaCidades.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        try (InputStream input = new URL(url).openStream()) {
            ListaCidades listaCidades = (ListaCidades) unmarshaller.unmarshal(input);
            return listaCidades.getCidades();
        }
    }

    @Recover
    public List<Cidade> recoverBuscaCidade(Exception e, String nome) {
        log.error("Fallback ativado para recoverBuscaCidade com nome: {}. Erro: {}", nome, e.getMessage());
        return Collections.emptyList();
    }

    @Retryable(value = { Exception.class }, maxAttempts = 3, backoff = @Backoff(delay = 5000))
    public PrevisaoCidade previsaoPelaCidadeId(int cidadeId) throws Exception {
        String url = previsaoUrl + cidadeId + "/previsao.xml";

        URL apiUrl = new URL(url);
        JAXBContext jaxbContext = JAXBContext.newInstance(PrevisaoCidade.class, PrevisaoDia.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (PrevisaoCidade) unmarshaller.unmarshal(apiUrl);
    }

    @Recover
    public PrevisaoCidade recoverPrevisaoPelaCidadeId(Exception e, int cidadeId) {
        log.error("Fallback ativado para previsaoPelaCidadeId com cidadeId: {}. Erro: {}", cidadeId, e.getMessage());
        return null;
    }

    @Retryable(value = { Exception.class }, maxAttempts = 3, backoff = @Backoff(delay = 5000))
    public PrevisaoOndas previsaoOndas(int cidadeId) throws Exception {
        String url = previsaoUrl + cidadeId + "/dia/0/ondas.xml";

        JAXBContext context = JAXBContext.newInstance(PrevisaoOndas.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        try (InputStream input = new URL(url).openStream()) {
            return (PrevisaoOndas) unmarshaller.unmarshal(input);
        }
    }

    @Recover
    public PrevisaoOndas recoverPrevisaoOndas(Exception e, int cidadeId) {
        log.error("Fallback ativado para previsaoOndas com cidadeId: {}. Erro: {}", cidadeId, e.getMessage());
        return null;
    }
}
