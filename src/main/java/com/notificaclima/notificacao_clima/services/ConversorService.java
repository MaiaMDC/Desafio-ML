package com.notificaclima.notificacao_clima.services;

import com.notificaclima.notificacao_clima.dto.PrevisaoDTO;
import com.notificaclima.notificacao_clima.cptec.model.PrevisaoCidade;
import com.notificaclima.notificacao_clima.cptec.model.PrevisaoDia;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConversorService {

    public List<PrevisaoDTO> convertToForecastDTOs(PrevisaoCidade previsaoCidade) {
        return previsaoCidade.getPrevisoes()
                .stream()
                .map(this::convertToForecastDTO)
                .collect(Collectors.toList());
    }

    public PrevisaoDTO convertToForecastDTO(PrevisaoDia dia) {
        PrevisaoDTO dto = new PrevisaoDTO();
        dto.setData(dia.getDia());
        dto.setMinima(dia.getMinima());
        dto.setMaxima(dia.getMaxima());
        dto.setTempo(dia.getTempo());
        return dto;
    }
}


