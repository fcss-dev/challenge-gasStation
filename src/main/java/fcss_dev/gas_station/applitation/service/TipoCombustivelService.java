package fcss_dev.gas_station.applitation.service;

import fcss_dev.gas_station.applitation.model.TipoCombustivel;
import fcss_dev.gas_station.applitation.repository.TipoCombustivelRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class TipoCombustivelService {
    @Autowired
    private TipoCombustivelRepository repository;

    // Create
    public TipoCombustivel salvar(TipoCombustivel tipo){
        if (tipo.getNome() == null || tipo.getPrecoPorLitro() == null) {
            throw new IllegalArgumentException("Nome e preço são obrigatórios");
        }

        return repository.save(tipo);
    }
    // Read
    public List<TipoCombustivel> listarTodos(){
        return repository.findAll();
    }
    public Optional<TipoCombustivel> listarPorId(Long id){
        return repository.findById(id);
    }

    // update
    public TipoCombustivel atualizar(TipoCombustivel tipo){
        return repository.save(tipo);
    }

    // Delete
    public void deletar(Long id){
        repository.deleteById(id);
    }
}
