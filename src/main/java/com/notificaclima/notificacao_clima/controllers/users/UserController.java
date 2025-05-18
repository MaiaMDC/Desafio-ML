package com.notificaclima.notificacao_clima.controllers.users;

import com.notificaclima.notificacao_clima.domain.users.Users;
import com.notificaclima.notificacao_clima.domain.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping
    public Users registerUser(@RequestBody Users users){
        return usersRepository.save(users);
    }

    @GetMapping
    public List<Users> getAllUser() {
        return usersRepository.findAll();
    }

    @GetMapping("/{id}")
    public Users getUser(@PathVariable Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}/opt")
    public ResponseEntity<String> changeOpt(
            @PathVariable Long id,
            @RequestBody Map<String, Boolean> body) {

        if (!body.containsKey("opt")) {
            return ResponseEntity.badRequest().body("Campo 'opt' é obrigatório");
        }

        Boolean opt = body.get("opt");

        Optional<Users> optionalUsuario = usersRepository.findById(id);
        if (optionalUsuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Users usuario = optionalUsuario.get();
        usuario.setOpt(opt);
        usersRepository.save(usuario);

        return ResponseEntity.ok("Status atualizado com sucesso");
    }

}
