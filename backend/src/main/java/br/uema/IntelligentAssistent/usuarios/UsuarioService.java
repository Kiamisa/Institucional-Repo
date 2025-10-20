package br.uema.IntelligentAssistent.usuarios;

import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> listarTodos(){
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não criado ou nao foi encontrado com o id: " + id));
    }

    public Usuario criar(Usuario usuario){

        if (usuario.getEmail() == null || !usuario.getEmail().endsWith("@ppg.uema.br")){
            throw new RuntimeException("Email inválido para cadastro.");
        }

        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()){
            throw new RuntimeException("Já existe um usuário cadastrado com este email.");
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioDetails){
        Usuario usuarioExistente = buscarPorId(id);

        usuarioExistente.setNome(usuarioDetails.getNome());
        //Não permite atualizar email para evitar conflitos

        //usuarioExistente.setEmail(usuarioDetails.getEmail());
        usuarioExistente.setPerfil(usuarioDetails.getPerfil());
        //Será refatorado, talvez tenha que ser retirado e a atualização será somente em documentos
        usuarioExistente.setSetor(usuarioDetails.getSetor());

        if (StringUtils.hasText(usuarioDetails.getSenha())){
            usuarioExistente.setSenha(passwordEncoder.encode(usuarioDetails.getSenha()));
        }
        return usuarioRepository.save(usuarioExistente);
    }

    public void deletarUsuario(Long id){
        if (!usuarioRepository.existsById(id)){
            throw new RuntimeException("Usuário não encontrado com o id: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}
