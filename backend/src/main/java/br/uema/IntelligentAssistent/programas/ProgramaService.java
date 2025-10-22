package br.uema.IntelligentAssistent.programas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramaService {
    @Autowired
    private ProgramaRepository programaRepository;

    public List<Programa> listarTodos(){
        return programaRepository.findAll();
    }

    public Programa buscarPorId(Long id){
        return programaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Programa não encontrado com o id: " + id));
    }

    public Programa criarPrograma(Programa programa){
        return programaRepository.save(programa);
    }

    public Programa atualizarPrograma(Long id, Programa programaDetails ){
        Programa programa = buscarPorId(id);
        programa.setNome(programaDetails.getNome());
        programa.setSigla(programaDetails.getSigla());
        return programaRepository.save(programa);
    }

    public void deletarPrograma(Long id){
        if (!programaRepository.existsById(id)){
            throw new RuntimeException("Programa não criado ou encontrado com o id: " + id);
        }
        Programa salvo = buscarPorId(id);
        programaRepository.delete(salvo);
    }
}
