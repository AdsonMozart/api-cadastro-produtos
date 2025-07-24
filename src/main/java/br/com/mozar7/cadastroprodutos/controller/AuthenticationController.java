package br.com.mozar7.cadastroprodutos.controller;

import br.com.mozar7.cadastroprodutos.model.dto.AuthenticationDTO;
import br.com.mozar7.cadastroprodutos.model.dto.RegisterDTO;
import br.com.mozar7.cadastroprodutos.model.user.User;
import br.com.mozar7.cadastroprodutos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Validated AuthenticationDTO dados) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Validated RegisterDTO dados) {
        if(this.userRepository.findByLogin(dados.login()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(dados.senha());
        User newUser = new User(dados.login(), encryptedPassword, dados.role());
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
