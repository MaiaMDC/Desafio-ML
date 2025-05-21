package com.notificaclima.notificacao_clima.scheduler;

import com.notificaclima.notificacao_clima.entity.Usuarios;
import com.notificaclima.notificacao_clima.repository.UsuarioRepository;
import com.notificaclima.notificacao_clima.services.CptecService;
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
    private UsuarioRepository userRepository;

    @Autowired
    private NotificacaoService notificacaoService;

    private static final Logger log = LoggerFactory.getLogger(CptecService.class);

    @Scheduled(cron = "0 * * * * *")
    public void verificarAgendamentos() {
        LocalTime agora = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);

        List<Usuarios> usuarios = userRepository.findAll();
        for (Usuarios user : usuarios) {
            if (user.getOpt() && agora.equals(user.getHorarioNotificacao())) {
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