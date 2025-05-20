package com.notificaclima.notificacao_clima.scheduler;

import com.notificaclima.notificacao_clima.domain.Users;
import com.notificaclima.notificacao_clima.domain.UsersRepository;
import com.notificaclima.notificacao_clima.services.NotificacaoService;
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

    @Scheduled(cron = "0 * * * * *") // Executa a cada minuto
    public void verificarAgendamentos() {
        LocalTime agora = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);

        List<Users> usuarios = userRepository.findAll();
        for (Users user : usuarios) {
            System.out.println("Verificando usuário: " + user.getNome() + " - OptIn: " + user.getIsOpt() + " - Horário: " + user.getHorarioNotificacao());
            if (user.getIsOpt() && agora.equals(user.getHorarioNotificacao())) {
                try {
                    System.out.println("Método enviarNotificacao chamado");
                    notificacaoService.enviarNotificacao(user);
                    System.out.println("Notificação enviada para o usuário: " + user.getNome());
                } catch (Exception e) {
                    System.err.println("Erro ao enviar notificação para o usuário: " + user.getNome());
                    e.printStackTrace();
                }
            }
        }
    }
}

