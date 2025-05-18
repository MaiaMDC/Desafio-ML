package com.notificaclima.notificacao_clima.controllers.users.cptec;

import com.notificaclima.notificacao_clima.services.CptecClient;
import com.notificaclima.notificacao_clima.xml.Cidade;
import com.notificaclima.notificacao_clima.xml.PrevisaoCidade;
import com.notificaclima.notificacao_clima.xml.PrevisaoOndas;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/clima")
public class CptecController {

    private final CptecClient cptecClient;

    public CptecController(CptecClient cptecClient) {
        this.cptecClient = cptecClient;
    }

    @GetMapping("/city")
    public List<Cidade> findCity(@RequestParam String nome) throws Exception {
        List<Cidade> cidades = cptecClient.findCity(nome);
        cidades.forEach(c -> System.out.println("ID: " + c.getId() + ", Nome: " + c.getNome() + ", UF: " + c.getUf()));
        return cidades;
    }

    @GetMapping("/previsao")
    public ResponseEntity<PrevisaoCidade> getPrevisao(@RequestParam int cidadeId) throws Exception {
            PrevisaoCidade previsao = cptecClient.getPrevisaoByCityId(cidadeId);
            return ResponseEntity.ok(previsao);
    }


    @GetMapping("/ondas")
    public PrevisaoOndas getPrevisaoOndas( @RequestParam int cidadeId) throws Exception {
        return cptecClient.getPrevisaoOndas(cidadeId);
    }

}
