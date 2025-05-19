package com.notificaclima.notificacao_clima.controllers.notificacao;

import com.notificaclima.notificacao_clima.domain.users.Users;
import com.notificaclima.notificacao_clima.domain.users.UsersRepository;
import com.notificaclima.notificacao_clima.services.NotificacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {

    private final NotificacaoService notificacaoService;
    private final UsersRepository usersRepository;

    public NotificacaoController(NotificacaoService notificacaoService, UsersRepository usersRepository) {
        this.notificacaoService = notificacaoService;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/enviar/{nomeCidade}")
    public ResponseEntity<String> enviarNotificacao(@PathVariable String nomeCidade) {
        try {
            Users user = usersRepository.findByCidade(nomeCidade);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Usuário com cidade '" + nomeCidade + "' não encontrado.");
            }

            notificacaoService.enviarNotificacao(user);
            return ResponseEntity.ok("Notificação enviada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao enviar notificação: " + e.getMessage());
        }
    }
}

