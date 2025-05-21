package com.notificaclima.notificacao_clima.controllers.cptec;

import com.notificaclima.notificacao_clima.services.CptecClientService;
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

    private final CptecClientService cptecClientService;

    public CptecController(CptecClientService cptecClientService) {
        this.cptecClientService = cptecClientService;
    }

    @GetMapping("/cidade")
    public List<Cidade> findCity(@RequestParam String nome) throws Exception {
        List<Cidade> cidades = cptecClientService.findCity(nome);
        return cidades;
    }

    @GetMapping("/previsao")
    public ResponseEntity<PrevisaoCidade> getPrevisao(@RequestParam int cidadeId) throws Exception {
            PrevisaoCidade previsao = cptecClientService.getPrevisaoByCityId(cidadeId);
            return ResponseEntity.ok(previsao);
    }


    @GetMapping("/ondas")
    public PrevisaoOndas getPrevisaoOndas( @RequestParam int cidadeId) throws Exception {
        return cptecClientService.getPrevisaoOndas(cidadeId);
    }

}
