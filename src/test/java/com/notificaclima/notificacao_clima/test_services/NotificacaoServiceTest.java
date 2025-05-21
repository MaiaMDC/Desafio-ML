package com.notificaclima.notificacao_clima.test_services;

import com.notificaclima.notificacao_clima.entity.Usuarios;
import com.notificaclima.notificacao_clima.dto.NotificacaoClimaDTO;
import com.notificaclima.notificacao_clima.dto.PrevisaoDTO;
import com.notificaclima.notificacao_clima.services.ConversorService;
import com.notificaclima.notificacao_clima.services.CptecService;
import com.notificaclima.notificacao_clima.services.NotificacaoService;
import com.notificaclima.notificacao_clima.cptec.model.Cidade;
import com.notificaclima.notificacao_clima.cptec.model.PrevisaoCidade;
import com.notificaclima.notificacao_clima.cptec.model.PrevisaoOndas;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificacaoServiceTest {

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @Mock
    private CptecService cptecClient;

    @Mock
    private ConversorService converter;

    @InjectMocks
    private NotificacaoService notificacaoService;

    @Test
    void deveMontarENotificarUsuarioCorretamente() throws Exception {
        Usuarios user = new Usuarios();
        user.setNome("João");
        user.setCidade("Santos");
        user.setOpt(true);
        user.setLitoral(true);

        Cidade cidade = new Cidade();
        cidade.setId(123);
        cidade.setNome("Santos");

        when(cptecClient.buscaCidade("Santos")).thenReturn(List.of(cidade));

        PrevisaoDTO previsao = new PrevisaoDTO();
        previsao.setData("2025-05-20");
        previsao.setMinima(15);
        previsao.setMaxima(25);
        previsao.setTempo("Ensolarado");
        when(cptecClient.previsaoPelaCidadeId(123)).thenReturn(new PrevisaoCidade());
        when(converter.convertToForecastDTOs(any())).thenReturn(List.of(previsao));

        PrevisaoOndas ondas = new PrevisaoOndas();
        when(cptecClient.previsaoOndas(123)).thenReturn(ondas);

        notificacaoService.enviarNotificacao(user);

        ArgumentCaptor<NotificacaoClimaDTO> captor = ArgumentCaptor.forClass(NotificacaoClimaDTO.class);
        verify(messagingTemplate).convertAndSend(eq("/notificacoes/clima"), captor.capture());

        NotificacaoClimaDTO dtoEnviado = captor.getValue();

        assertEquals("João", dtoEnviado.getUsuario());
        assertEquals("Santos", dtoEnviado.getCidade());
        assertEquals("Ensolarado", dtoEnviado.getPrevisoes().get(0).getTempo());
        assertNotNull(dtoEnviado.getOndas());
    }
}

