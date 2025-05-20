package com.notificaclima.notificacao_clima.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Cidade é obrigatória")
    private String cidade;

    @NotNull(message = "Horário de notificação é obrigatório")
    private LocalTime horarioNotificacao;

    @NotNull(message = "O campo opt é obrigatório")
    private Boolean isOpt;

    @NotNull(message = "O campo isCoastline é obrigatório")
    private Boolean isCoastline;

    public Users() {
    }

    public Users(Long id, String nome, String cidade, LocalTime horarioNotificacao, Boolean isOpt, Boolean isCoastline) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.horarioNotificacao = horarioNotificacao;
        this.isOpt = isOpt;
        this.isCoastline = isCoastline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public LocalTime getHorarioNotificacao() {
        return horarioNotificacao;
    }

    public void setHorarioNotificacao(LocalTime horarioNotificacao) {
        this.horarioNotificacao = horarioNotificacao;
    }

    public Boolean getIsOpt() {
        return isOpt;
    }

    public void setIsOpt(Boolean opt) {
        this.isOpt = opt;
    }

    public Boolean getIsCoastline() {
        return isCoastline;
    }

    public void setIsCoastline(Boolean isCoastline) {
        this.isCoastline = isCoastline;
    }
}
