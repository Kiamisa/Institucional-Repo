package br.uema.ppg.IntelligentAssistent.setores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetorRepository  extends JpaRepository<Setor, Long> {
    // Terá os métodos CRUD findAll(), findById(), save(), deleteById()
}
