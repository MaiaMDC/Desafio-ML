package com.notificaclima.notificacao_clima.test_services;

import com.notificaclima.notificacao_clima.domain.Users;
import com.notificaclima.notificacao_clima.dto.NotificacaoClimaDTO;
import com.notificaclima.notificacao_clima.dto.PrevisaoDTO;
import com.notificaclima.notificacao_clima.services.ConverterService;
import com.notificaclima.notificacao_clima.services.CptecClientService;
import com.notificaclima.notificacao_clima.services.NotificacaoService;
import com.notificaclima.notificacao_clima.xml.Cidade;
import com.notificaclima.notificacao_clima.xml.PrevisaoCidade;
import com.notificaclima.notificacao_clima.xml.PrevisaoOndas;
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
    private CptecClientService cptecClient;

    @Mock
    private ConverterService converter;

    @InjectMocks
    private NotificacaoService notificacaoService;

    @Test
    void deveMontarENotificarUsuarioCorretamente() throws Exception {
        // Arrange - Criar um usuário fictício
        Users user = new Users();
        user.setNome("João");
        user.setCidade("Santos");
        user.setIsOpt(true);
        user.setIsCoastline(true); // litoral, para testar PrevisaoOndas

        // Cidade encontrada
        Cidade cidade = new Cidade();
        cidade.setId(123);
        cidade.setNome("Santos");

        when(cptecClient.findCity("Santos")).thenReturn(List.of(cidade));

        // Simula a previsão
        PrevisaoDTO previsao = new PrevisaoDTO();
        previsao.setData("2025-05-20");
        previsao.setMinima(15);
        previsao.setMaxima(25);
        previsao.setTempo("Ensolarado");
        when(cptecClient.getPrevisaoByCityId(123)).thenReturn(new PrevisaoCidade()); // retornará um objeto vazio
        when(converter.convertToForecastDTOs(any())).thenReturn(List.of(previsao));

        // Simula previsão de ondas
        PrevisaoOndas ondas = new PrevisaoOndas();
        when(cptecClient.getPrevisaoOndas(123)).thenReturn(ondas);

        // Act - Chama o método
        notificacaoService.enviarNotificacao(user);

        // Assert - Verifica se o template foi chamado corretamente
        ArgumentCaptor<NotificacaoClimaDTO> captor = ArgumentCaptor.forClass(NotificacaoClimaDTO.class);
        verify(messagingTemplate).convertAndSend(eq("/notificacoes/clima"), captor.capture());

        NotificacaoClimaDTO dtoEnviado = captor.getValue();

        assertEquals("João", dtoEnviado.getUsuario());
        assertEquals("Santos", dtoEnviado.getCidade());
        assertEquals("Ensolarado", dtoEnviado.getPrevisoes().get(0).getTempo());
        assertNotNull(dtoEnviado.getOndas());
    }
}

