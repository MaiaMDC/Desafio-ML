package com.notificaclima.notificacao_clima.repository;

import com.notificaclima.notificacao_clima.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository <Users, Long>{

    Users findByCidade(String cidade);
}
