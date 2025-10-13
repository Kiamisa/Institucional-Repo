package br.uema.IntelligentAssistent.auth;

import br.uema.IntelligentAssistent.usuarios.Usuario;
import br.uema.IntelligentAssistent.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        // Busca o usuario pelo email
        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()->new RuntimeException("Usuário não encontrado."));

        // Comparação da senha enviada com a senha armazenada no BD
        if (passwordEncoder.matches(loginRequest.getPassword(), usuario.getSenha())) {
            //Se sim, gera o token de acesso
            String token = tokenService.generateToken(usuario);

            //Cria objeto de resposta para enviar o token
            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);
        } else{
            // Se a senha estiver incorreta, retornar erro 401
            return ResponseEntity.status(401).body("Credenciais inválidas.");
        }
    }

}
