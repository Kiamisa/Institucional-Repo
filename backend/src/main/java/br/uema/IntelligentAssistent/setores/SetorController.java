package br.uema.IntelligentAssistent.setores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/setores")
public class SetorController {

    @Autowired
    private SetorService setorService;

    //Endpoint de listagem dos setores (GET /setores)
    @GetMapping
    public List<Setor> listarTodos(){
        return setorService.listarTodos();
    }

    //Endpoint para busca de um setor pelo ID (GET /setores/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Setor> buscarPorId(@PathVariable Long id){
        Setor setor = setorService.buscarPorId(id);
        return ResponseEntity.ok(setor);
    }

    //Endpoint para criação de um novo setor (POST /setores)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Setor criar(@RequestBody Setor setor){
        return setorService.criarSetor(setor);
    }

    //Endpoint de atualização de um setor (PUT /setores/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Setor> atualizar(@PathVariable Long id, @RequestBody Setor setorDetails){
        Setor setorAtualizado = setorService.atualizarSetor(id, setorDetails);
        return ResponseEntity.ok(setorAtualizado);
    }

    //Endpoint para exclusão de um setor (DELETE /setores/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        setorService.deletarSetor(id);
        return ResponseEntity.noContent().build();// Retorna status 204 (No content)
    }
}
