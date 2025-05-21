package com.notificaclima.notificacao_clima.test_services;

import com.notificaclima.notificacao_clima.dto.PrevisaoDTO;
import com.notificaclima.notificacao_clima.services.ConverterService;
import com.notificaclima.notificacao_clima.cptec.model.PrevisaoCidade;
import com.notificaclima.notificacao_clima.cptec.model.PrevisaoDia;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Collections;

public class ConverterServiceTest {

    private final ConverterService converterService = new ConverterService();

    @Test
    public void testConvertToForecastDTO() {
        PrevisaoDia dia = new PrevisaoDia();
        dia.setDia("2025-05-20");
        dia.setMinima(15);
        dia.setMaxima(25);
        dia.setTempo("Ensolarado");

        PrevisaoDTO dto = converterService.convertToForecastDTO(dia);

        assertEquals("2025-05-20", dto.getData());
        assertEquals(15, dto.getMinima());
        assertEquals(25, dto.getMaxima());
        assertEquals("Ensolarado", dto.getTempo());
    }

    @Test
    public void testConvertToForecastDTOs() {
        PrevisaoDia dia = new PrevisaoDia();
        dia.setDia("2025-05-21");
        dia.setMinima(17);
        dia.setMaxima(27);
        dia.setTempo("Nublado");

        PrevisaoCidade previsaoCidade = new PrevisaoCidade();
        previsaoCidade.setPrevisoes(Collections.singletonList(dia));

        List<PrevisaoDTO> dtos = converterService.convertToForecastDTOs(previsaoCidade);

        assertNotNull(dtos);
        assertEquals(1, dtos.size());
        PrevisaoDTO dto = dtos.get(0);
        assertEquals("2025-05-21", dto.getData());
        assertEquals(17, dto.getMinima());
        assertEquals(27, dto.getMaxima());
        assertEquals("Nublado", dto.getTempo());
    }
}

