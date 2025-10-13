package br.uema.ppg.IntelligentAssistent.usuarios;

import br.uema.ppg.IntelligentAssistent.setores.Setor;
import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data //Anotação para gerar Getters, Setters, toString, etc.
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @ManyToOne(fetch = FetchType.LAZY)//relacionamento
    @JoinColumn(name = "setor_id", nullable = false) //Mapeamento da chave estrangeira
    private Setor setor;

    @Column(nullable = false)
    private String perfil;

    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    public void prePersist(){
        createdAt = OffsetDateTime.now();
    }
}
