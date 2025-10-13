package br.uema.ppg.IntelligentAssistent.usuarios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //Endpoint de listagem dos usuarios (GET /usuarios)
    @GetMapping
    public List<Usuario> listarTodos(){
        return usuarioService.listarTodos();
    }

    //Endpoint para busca de um usuario pelo ID (GET /usuarios/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id){
        Usuario  usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    //Endpoint para criação de um novo usuario (POST /usuarios)
    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario){
        Usuario usuarioCriado = usuarioService.criar(usuario);
        return new ResponseEntity<>(usuarioCriado, HttpStatus.CREATED);
    }

    //Endpoint de atualização de um usuario (PUT /usuarios/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuarioDetails){
        Usuario usuarioAtualizado = usuarioService.atualizarUsuario(id, usuarioDetails);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    //Endpoint para exclusão de um usuario (DELETE /usuarios/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
