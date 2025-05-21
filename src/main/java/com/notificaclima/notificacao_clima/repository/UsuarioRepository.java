package com.notificaclima.notificacao_clima.repository;

import com.notificaclima.notificacao_clima.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository <Usuarios, Long>{

    Usuarios findByCidade(String cidade);
}
