package com.notificaclima.notificacao_clima.services;

import com.notificaclima.notificacao_clima.entity.Usuarios;
import com.notificaclima.notificacao_clima.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuarios buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com id: " + id));
    }

    public Usuarios atualizarUsuario(Long id, Usuarios updatedUser) {
        Usuarios usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com id: " + id));

        usuario.setNome(updatedUser.getNome());
        usuario.setCidade(updatedUser.getCidade());
        usuario.setHorarioNotificacao(updatedUser.getHorarioNotificacao());
        usuario.setOpt(updatedUser.getOpt());
        usuario.setLitoral(updatedUser.getLitoral());

        return usuarioRepository.save(usuario);
    }

    public void alterarOpt(Long id, Boolean opt) {
        Usuarios usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com id: " + id));

        usuario.setOpt(opt);
        usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado com id: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}
