package com.notificaclima.notificacao_clima.test_services;

import com.notificaclima.notificacao_clima.entity.Usuarios;
import com.notificaclima.notificacao_clima.repository.UsuarioRepository;
import com.notificaclima.notificacao_clima.services.UsuarioService;
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
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuarios user;

    @BeforeEach
    void setUp() {
        user = new Usuarios();
        user.setId(1L);
        user.setNome("João");
        user.setCidade("São Paulo");
        user.setHorarioNotificacao(LocalTime.now());
        user.setOpt(true);
        user.setLitoral(false);
    }

    @Test
    void deveRetornarUsuarioQuandoIdExistir() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(user));

        Usuarios result = usuarioService.buscarUsuarioPorId(1L);

        assertEquals(user, result);
        verify(usuarioRepository).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExistir() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> usuarioService.buscarUsuarioPorId(1L));
    }

    @Test
    void deveAtualizarEDepositarDadosCorretamente() {
        Usuarios updated = new Usuarios();
        updated.setNome("Maria");
        updated.setCidade("Rio de Janeiro");
        updated.setHorarioNotificacao(LocalTime.parse("10:00"));
        updated.setOpt(false);
        updated.setLitoral(true);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(user));
        when(usuarioRepository.save(any())).thenReturn(user);

        Usuarios result = usuarioService.atualizarUsuario(1L, updated);

        assertEquals("Maria", result.getNome());
        assertEquals("Rio de Janeiro", result.getCidade());
        assertEquals(LocalTime.parse("10:00"), result.getHorarioNotificacao());
        assertFalse(result.getOpt());
        assertTrue(result.getLitoral());
    }

    @Test
    void deveLancarExcecaoSeNaoEncontrado() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        Usuarios updated = new Usuarios();
        assertThrows(EntityNotFoundException.class, () -> usuarioService.atualizarUsuario(1L, updated));
    }

    @Test
    void deveAtualizarOpt() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(user));

        usuarioService.alterarOpt(1L, false);

        assertFalse(user.getOpt());
        verify(usuarioRepository).save(user);
    }

    @Test
    void alterarOpt_deveLancarExcecaoSeNaoEncontrado() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> usuarioService.alterarOpt(1L, true));
    }

    @Test
    void deletarUsuario_deveDeletarSeExistir() {
        when(usuarioRepository.existsById(1L)).thenReturn(true);

        usuarioService.deletarUsuario(1L);

        verify(usuarioRepository).deleteById(1L);
    }

    @Test
    void deletarUsuario_deveLancarExcecaoSeNaoExistir() {
        when(usuarioRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> usuarioService.deletarUsuario(1L));
    }
}

