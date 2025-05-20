package com.notificaclima.notificacao_clima.scheduler;

import com.notificaclima.notificacao_clima.domain.Users;
import com.notificaclima.notificacao_clima.domain.UsersRepository;
import com.notificaclima.notificacao_clima.services.CptecClientService;
import com.notificaclima.notificacao_clima.services.NotificacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class NotificacaoScheduler {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private NotificacaoService notificacaoService;

    private static final Logger log = LoggerFactory.getLogger(CptecClientService.class);

    @Scheduled(cron = "0 * * * * *") // Executa a cada minuto
    public void verificarAgendamentos() {
        LocalTime agora = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);

        List<Users> usuarios = userRepository.findAll();
        for (Users user : usuarios) {
            if (user.getIsOpt() && agora.equals(user.getHorarioNotificacao())) {
                try {
                    notificacaoService.enviarNotificacao(user);
                } catch (Exception e) {
                    log.error("Erro ao enviar notificação para o usuário: {}", user.getNome());
                    e.printStackTrace();
                }
            }
        }
    }
}