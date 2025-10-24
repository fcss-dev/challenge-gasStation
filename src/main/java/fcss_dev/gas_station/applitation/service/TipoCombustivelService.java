package fcss_dev.gas_station.applitation.service;

import fcss_dev.gas_station.applitation.exceptions.DadosInvalidosException;
import fcss_dev.gas_station.applitation.exceptions.NenhumRegistroEncontradoException;
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
            throw new DadosInvalidosException("Nome e preço são obrigatórios");
        }

        return repository.save(tipo);
    }
    // Read
    public List<TipoCombustivel> listarTodos(){
        List<TipoCombustivel> tipos = repository.findAll();

        if (tipos.isEmpty()) {
            throw new NenhumRegistroEncontradoException("Nenhum tipo de combustível cadastrado");
        }

        return tipos;
    }

    public Optional<TipoCombustivel> listarPorId(Long id){
        Optional<TipoCombustivel> tipo = repository.findById(id);
        if (tipo.isEmpty()) {
            throw new NenhumRegistroEncontradoException("Tipo de combustível com ID " + id + " não encontrado");
        }
        return tipo;
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
