package br.uema.IntelligentAssistent.documentos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    // Terá os métodos CRUD findAll(), findById(), save(), deleteById()
}
