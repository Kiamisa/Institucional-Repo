package br.uema.IntelligentAssistent.programas;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data //Anotação para gerar Getters, Setters, toString, etc.
@Entity
@Table(name = "programas")
public class Programa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String sigla;

    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    public void prePersist(){
        createdAt = OffsetDateTime.now();
    }
}
