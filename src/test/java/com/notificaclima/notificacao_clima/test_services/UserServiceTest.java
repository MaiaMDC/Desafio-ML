package com.notificaclima.notificacao_clima.test_services;

import com.notificaclima.notificacao_clima.domain.Users;
import com.notificaclima.notificacao_clima.domain.UsersRepository;
import com.notificaclima.notificacao_clima.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UserService userService;

    private Users user;

    @BeforeEach
    void setUp() {
        user = new Users();
        user.setId(1L);
        user.setNome("João");
        user.setCidade("São Paulo");
        user.setHorarioNotificacao(LocalTime.now());
        user.setIsOpt(true);
        user.setIsCoastline(false);
    }

    @Test
    void deveRetornarUsuarioQuandoIdExistir() {
        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));

        Users result = userService.buscarUsuarioPorId(1L);

        assertEquals(user, result);
        verify(usersRepository).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExistir() {
        when(usersRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.buscarUsuarioPorId(1L));
    }

    @Test
    void deveAtualizarEDepositarDadosCorretamente() {
        Users updated = new Users();
        updated.setNome("Maria");
        updated.setCidade("Rio de Janeiro");
        updated.setHorarioNotificacao(LocalTime.parse("10:00"));
        updated.setIsOpt(false);
        updated.setIsCoastline(true);

        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));
        when(usersRepository.save(any())).thenReturn(user);

        Users result = userService.atualizarUsuario(1L, updated);

        assertEquals("Maria", result.getNome());
        assertEquals("Rio de Janeiro", result.getCidade());
        assertEquals(LocalTime.parse("10:00"), result.getHorarioNotificacao());
        assertFalse(result.getIsOpt());
        assertTrue(result.getIsCoastline());
    }

    @Test
    void deveLancarExcecaoSeNaoEncontrado() {
        when(usersRepository.findById(1L)).thenReturn(Optional.empty());

        Users updated = new Users();
        assertThrows(EntityNotFoundException.class, () -> userService.atualizarUsuario(1L, updated));
    }

    @Test
    void deveAtualizarOpt() {
        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.alterarOpt(1L, false);

        assertFalse(user.getIsOpt());
        verify(usersRepository).save(user);
    }

    @Test
    void alterarOpt_deveLancarExcecaoSeNaoEncontrado() {
        when(usersRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.alterarOpt(1L, true));
    }

    @Test
    void deletarUsuario_deveDeletarSeExistir() {
        when(usersRepository.existsById(1L)).thenReturn(true);

        userService.deletarUsuario(1L);

        verify(usersRepository).deleteById(1L);
    }

    @Test
    void deletarUsuario_deveLancarExcecaoSeNaoExistir() {
        when(usersRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> userService.deletarUsuario(1L));
    }
}

