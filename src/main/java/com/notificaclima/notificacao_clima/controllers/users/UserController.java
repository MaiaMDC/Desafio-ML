package com.notificaclima.notificacao_clima.controllers.users;

import com.notificaclima.notificacao_clima.entity.Usuarios;
import com.notificaclima.notificacao_clima.repository.UsuarioRepository;
import com.notificaclima.notificacao_clima.services.UsuarioService;
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
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuarios> cadastraUsuario(@Valid @RequestBody Usuarios usuarios){
        Usuarios savedUser = usuarioRepository.save(usuarios);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping
    public List<Usuarios> buscaTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> getUser(@PathVariable Long id) {
        Usuarios user = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuarios> atualizaUsuario(@PathVariable Long id, @Valid @RequestBody Usuarios updatedUser) {
        Usuarios usuarioAtualizado = usuarioService.atualizarUsuario(id, updatedUser);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @PutMapping("/{id}/opt")
    public ResponseEntity<String> alteraOpt(@PathVariable Long id, @Valid @RequestBody Map<String, Boolean> body) {
        if (!body.containsKey("opt")) {
            return ResponseEntity.badRequest().body("Campo 'opt' é obrigatório");
        }
        usuarioService.alterarOpt(id, body.get("opt"));
        return ResponseEntity.ok("Status atualizado com sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
