package com.notificaclima.notificacao_clima.services;

import com.notificaclima.notificacao_clima.domain.Users;
import com.notificaclima.notificacao_clima.dto.NotificacaoClimaDTO;
import com.notificaclima.notificacao_clima.dto.PrevisaoDTO;
import com.notificaclima.notificacao_clima.xml.Cidade;
import com.notificaclima.notificacao_clima.xml.PrevisaoOndas;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotificacaoService {

    private final SimpMessagingTemplate messagingTemplate;
    private final CptecClientService cptecClient;
    private final ConverterService converter;

    public NotificacaoService(SimpMessagingTemplate messagingTemplate,
                              CptecClientService cptecClient,
                              ConverterService converter) {
        this.messagingTemplate = messagingTemplate;
        this.cptecClient = cptecClient;
        this.converter = converter;
    }

    public void enviarNotificacao(Users user) throws Exception {
        String nomeCidade = user.getCidade();

        List<Cidade> cidades = cptecClient.findCity(nomeCidade);
        if (cidades.isEmpty()) {
            throw new RuntimeException("Cidade não encontrada: " + nomeCidade);
        }

        Cidade cidade = cidades.get(0);
        int cidadeId = cidade.getId();

        List<PrevisaoDTO> previsoes = converter.convertToForecastDTOs(cptecClient.getPrevisaoByCityId(cidadeId));

        PrevisaoOndas ondas = null;
        if (user.getIsCoastline()) {
            ondas = cptecClient.getPrevisaoOndas(cidadeId);
        }

        NotificacaoClimaDTO notificacao = new NotificacaoClimaDTO(
                user.getNome(),
                nomeCidade,
                LocalDate.now().toString(),
                previsoes,
                ondas
        );

        messagingTemplate.convertAndSend("/notificacoes/clima", notificacao);
    }

}


