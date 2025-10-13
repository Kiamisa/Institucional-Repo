package br.uema.ppg.IntelligentAssistent.setores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetorService {
    @Autowired
    private SetorRepository setorRepository;

    public List<Setor> listarTodos(){
        return setorRepository.findAll();
    }

    public Setor buscarPorId(Long id){
        return setorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Setor não encontrado com o id: " + id));
    }

    public Setor criarSetor(Setor setor){
        return setorRepository.save(setor);
    }

    public Setor atualizarSetor(Long id, Setor setorDetails ){
        Setor setor = buscarPorId(id);
        setor.setNome(setorDetails.getNome());
        setor.setSigla(setorDetails.getSigla());
        return setorRepository.save(setor);
    }

    public void deletarSetor(Long id){
        if (!setorRepository.existsById(id)){
            throw new RuntimeException("Setor não criado ou encontrado com o id: " + id);
        }
        Setor salvo = buscarPorId(id);
        setorRepository.delete(salvo);
    }
}
