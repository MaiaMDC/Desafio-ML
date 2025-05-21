package com.notificaclima.notificacao_clima.controllers.users;

import com.notificaclima.notificacao_clima.entity.Users;
import com.notificaclima.notificacao_clima.repository.UsersRepository;
import com.notificaclima.notificacao_clima.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Users> registerUser(@Valid @RequestBody Users users){
        Users savedUser = usersRepository.save(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping
    public List<Users> getAllUser() {
        return usersRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUser(@PathVariable Long id) {
        Users user = userService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> atualizarUsuario(@PathVariable Long id,@Valid @RequestBody Users updatedUser) {
        Users usuarioAtualizado = userService.atualizarUsuario(id, updatedUser);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @PutMapping("/{id}/opt")
    public ResponseEntity<String> changeOpt(@PathVariable Long id,@Valid @RequestBody Map<String, Boolean> body) {
        if (!body.containsKey("opt")) {
            return ResponseEntity.badRequest().body("Campo 'opt' é obrigatório");
        }
        userService.alterarOpt(id, body.get("opt"));
        return ResponseEntity.ok("Status atualizado com sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
