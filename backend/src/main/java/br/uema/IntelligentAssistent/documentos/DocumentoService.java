package br.uema.IntelligentAssistent.documentos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoService {
    @Autowired
    private DocumentoRepository documentoRepository;

    public List<Documento> listarTodos(){
        return documentoRepository.findAll();
    }

    public Documento buscarPorId(Long id) {
        return documentoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Documento não existe ou não foi encontrado com o id: " + id));
    }

    public Documento criar(Documento documento) {
        return documentoRepository.save(documento);
    }

    public Documento atualizar(Long id, Documento documentoDetails) {
        Documento documentoExistente = buscarPorId(id);

        documentoExistente.setTitulo(documentoDetails.getTitulo());
        documentoExistente.setDescricao(documentoDetails.getDescricao());
        documentoExistente.setTipo(documentoDetails.getTipo());
        documentoExistente.setSetor(documentoDetails.getSetor());

        return documentoRepository.save(documentoExistente);
    }

    public void deletar(Long id) {
        if (!documentoRepository.existsById(id)) {
            throw new RuntimeException("Documento não existe ou não foi encontrado com o id: " + id);
        }
        documentoRepository.deleteById(id);
    }
}
