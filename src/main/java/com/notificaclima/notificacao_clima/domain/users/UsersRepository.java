package com.notificaclima.notificacao_clima.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository <Users, Long>{

    Users findByCidade(String cidade);
}
