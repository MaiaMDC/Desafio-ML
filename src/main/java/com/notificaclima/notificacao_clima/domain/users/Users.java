package com.notificaclima.notificacao_clima.domain.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cidade;
    private int messageTime;
    private Boolean opt;
    private Boolean isCoastline;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setMessageTime(int messageTime) {
        this.messageTime = messageTime;
    }

    public void setOpt(Boolean opt) {
        this.opt = opt;
    }

    public void setCoastline(Boolean coastline) {
        isCoastline = coastline;
    }
}
