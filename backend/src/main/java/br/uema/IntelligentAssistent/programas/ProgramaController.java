package br.uema.IntelligentAssistent.programas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programas")
public class ProgramaController {

    @Autowired
    private ProgramaService programaService;

    //Endpoint de listagem dos programas (GET /programas)
    @GetMapping
    public List<Programa> listarTodos(){
        return programaService.listarTodos();
    }

    //Endpoint para busca de um programa pelo ID (GET /programas/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Programa> buscarPorId(@PathVariable Long id){
        Programa programa = programaService.buscarPorId(id);
        return ResponseEntity.ok(programa);
    }

    //Endpoint para criação de um novo programa (POST /programas)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Programa criar(@RequestBody Programa programa){
        return programaService.criarPrograma(programa);
    }

    //Endpoint de atualização de um programa (PUT /programas/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Programa> atualizar(@PathVariable Long id, @RequestBody Programa programaDetails){
        Programa programaAtualizado = programaService.atualizarPrograma(id, programaDetails);
        return ResponseEntity.ok(programaAtualizado);
    }

    //Endpoint para exclusão de um programa (DELETE /programas/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        programaService.deletarPrograma(id);
        return ResponseEntity.noContent().build();// Retorna status 204 (No content)
    }
}
