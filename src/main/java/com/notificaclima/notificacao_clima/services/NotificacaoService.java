package com.notificaclima.notificacao_clima.services;

import com.notificaclima.notificacao_clima.entity.Usuarios;
import com.notificaclima.notificacao_clima.dto.NotificacaoClimaDTO;
import com.notificaclima.notificacao_clima.dto.PrevisaoDTO;
import com.notificaclima.notificacao_clima.cptec.model.Cidade;
import com.notificaclima.notificacao_clima.cptec.model.PrevisaoOndas;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotificacaoService {

    private final SimpMessagingTemplate messagingTemplate;
    private final CptecService cptecClient;
    private final ConversorService converter;

    public NotificacaoService(SimpMessagingTemplate messagingTemplate,
                              CptecService cptecClient,
                              ConversorService converter) {
        this.messagingTemplate = messagingTemplate;
        this.cptecClient = cptecClient;
        this.converter = converter;
    }

    public void enviarNotificacao(Usuarios user) throws Exception {
        String nomeCidade = user.getCidade();

        List<Cidade> cidades = cptecClient.buscaCidade(nomeCidade);
        if (cidades.isEmpty()) {
            throw new RuntimeException("Cidade não encontrada: " + nomeCidade);
        }

        Cidade cidade = cidades.get(0);
        int cidadeId = cidade.getId();

        List<PrevisaoDTO> previsoes = converter.convertToForecastDTOs(cptecClient.previsaoPelaCidadeId(cidadeId));

        PrevisaoOndas ondas = null;
        if (user.getLitoral()) {
            ondas = cptecClient.previsaoOndas(cidadeId);
        }

        NotificacaoClimaDTO notificacao = new NotificacaoClimaDTO(
                user.getNome(),
                nomeCidade,
                cidade.getUf(),
                previsoes,
                ondas
        );

        messagingTemplate.convertAndSend("/notificacoes/clima", notificacao);
    }

}


