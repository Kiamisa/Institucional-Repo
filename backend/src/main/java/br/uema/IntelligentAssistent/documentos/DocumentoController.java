package br.uema.IntelligentAssistent.documentos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {
    @Autowired
    private DocumentoService documentoService;

    //Endpoint de listagem dos documentos (GET /documentos)
    @GetMapping
    public List<Documento> listarDocumentos() {
        return documentoService.listarTodos();
    }

    //Endpoint para busca de um documento pelo ID (GET /programas/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Documento> buscarDocumentoPorId(@PathVariable Long id) {
        Documento documento = documentoService.buscarPorId(id);
        return ResponseEntity.ok(documento);
    }

    //Endpoint para criação de um novo documento (POST /documentoss)
    @PostMapping
    public ResponseEntity<Documento> criar(@RequestBody Documento documento) {
        Documento documentoCriado = documentoService.criar(documento);
        return new  ResponseEntity<>(documentoCriado, HttpStatus.CREATED);
    }

    //Endpoint de atualização de um documento (PUT /documentos/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Documento> atualizar(@PathVariable Long id, @RequestBody Documento documento) {
        documentoService.atualizar(id, documento);
        return ResponseEntity.ok(documento);
    }

    //Endpoint para exclusão de um documento (DELETE /documentos/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Documento> deletar(@PathVariable Long id) {
        documentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
