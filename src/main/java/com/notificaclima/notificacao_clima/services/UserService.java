package com.notificaclima.notificacao_clima.services;

import com.notificaclima.notificacao_clima.entity.Users;
import com.notificaclima.notificacao_clima.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    public Users buscarUsuarioPorId(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com id: " + id));
    }

    public Users atualizarUsuario(Long id, Users updatedUser) {
        Users usuario = usersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com id: " + id));

        usuario.setNome(updatedUser.getNome());
        usuario.setCidade(updatedUser.getCidade());
        usuario.setHorarioNotificacao(updatedUser.getHorarioNotificacao());
        usuario.setIsOpt(updatedUser.getIsOpt());
        usuario.setIsCoastline(updatedUser.getIsCoastline());

        return usersRepository.save(usuario);
    }

    public void alterarOpt(Long id, Boolean opt) {
        Users usuario = usersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com id: " + id));

        usuario.setIsOpt(opt);
        usersRepository.save(usuario);
    }

    public void deletarUsuario(Long id) {
        if (!usersRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado com id: " + id);
        }
        usersRepository.deleteById(id);
    }
}
