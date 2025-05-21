package com.notificaclima.notificacao_clima.controllers.cptec;

import com.notificaclima.notificacao_clima.services.CptecService;
import com.notificaclima.notificacao_clima.cptec.model.Cidade;
import com.notificaclima.notificacao_clima.cptec.model.PrevisaoCidade;
import com.notificaclima.notificacao_clima.cptec.model.PrevisaoOndas;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/clima")
public class CptecController {

    private final CptecService cptecService;

    public CptecController(CptecService cptecService) {
        this.cptecService = cptecService;
    }

    @GetMapping("/cidade")
    public List<Cidade> buscaCidadePorNome(@RequestParam String nome) throws Exception {
        List<Cidade> cidades = cptecService.buscaCidade(nome);
        return cidades;
    }

    @GetMapping("/previsao")
    public ResponseEntity<PrevisaoCidade> previsaoPorCidadeId(@RequestParam int cidadeId) throws Exception {
            PrevisaoCidade previsao = cptecService.previsaoPelaCidadeId(cidadeId);
            return ResponseEntity.ok(previsao);
    }


    @GetMapping("/ondas")
    public PrevisaoOndas previsaoOndasPorCidadeId(@RequestParam int cidadeId) throws Exception {
        return cptecService.previsaoOndas(cidadeId);
    }

}
