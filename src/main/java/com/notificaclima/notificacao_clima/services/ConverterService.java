package com.notificaclima.notificacao_clima.services;

import com.notificaclima.notificacao_clima.dto.PrevisaoDTO;
import com.notificaclima.notificacao_clima.xml.PrevisaoCidade;
import com.notificaclima.notificacao_clima.xml.PrevisaoDia;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConverterService {

    public List<PrevisaoDTO> paraPrevisaoDTOs(PrevisaoCidade previsaoCidade) {
        return previsaoCidade.getPrevisoes()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    private PrevisaoDTO paraDTO(PrevisaoDia dia) {
        PrevisaoDTO dto = new PrevisaoDTO();
        dto.setData(dia.getDia());
        dto.setMinima(dia.getMinima());
        dto.setMaxima(dia.getMaxima());
        dto.setTempo(dia.getTempo());
        return dto;
    }
}


