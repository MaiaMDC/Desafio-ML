package com.notificaclima.notificacao_clima.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

@Entity
@Table(name = "usuarios")
public class Usuarios {

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
    private Boolean Opt;

    @NotNull(message = "O campo isCoastline é obrigatório")
    private Boolean litoral;

    public Usuarios() {
    }

    public Usuarios(Long id, String nome, String cidade, LocalTime horarioNotificacao, Boolean Opt, Boolean litoral) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.horarioNotificacao = horarioNotificacao;
        this.Opt = Opt;
        this.litoral = litoral;
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

    public Boolean getOpt() {
        return Opt;
    }

    public void setOpt(Boolean opt) {
        this.Opt = opt;
    }

    public Boolean getLitoral() {
        return litoral;
    }

    public void setLitoral(Boolean litoral) {
        this.litoral = litoral;
    }
}
