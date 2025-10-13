package br.uema.IntelligentAssistent.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    // Terá os métodos CRUD findAll(), findById(), save(), deleteById()
}
