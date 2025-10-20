package br.uema.IntelligentAssistent.programas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramaRepository  extends JpaRepository<Programa, Long> {
    // Terá os métodos CRUD findAll(), findById(), save(), deleteById()
}
