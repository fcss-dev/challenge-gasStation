package fcss_dev.gas_station.applitation.service;

import fcss_dev.gas_station.applitation.exceptions.DadosInvalidosException;
import fcss_dev.gas_station.applitation.exceptions.NenhumRegistroEncontradoException;
import fcss_dev.gas_station.applitation.exceptions.NomeJaExisteException;
import fcss_dev.gas_station.applitation.model.TipoCombustivel;
import fcss_dev.gas_station.applitation.repository.TipoCombustivelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoCombustivelService {

    @Autowired
    private TipoCombustivelRepository repository;

    // CREATE SERVICE - TIPO DE COMBUSTIVEL
    // Lógica que adiciona um novo registro de tipo de combustivel ao banco
    public TipoCombustivel salvar(TipoCombustivel tipo){
        if (tipo.getNome() == null || tipo.getPrecoPorLitro() == null) {
            throw new DadosInvalidosException("Nome e preço são obrigatórios");
        }
        if (repository.existsByNome(tipo.getNome())) {
            throw new NomeJaExisteException("O nome '" + tipo.getNome() + "' já existe.");
        }

        return repository.save(tipo);
    }


    // READ SERVICE - TIPO DE COMBUSTIVEL
    // Lógica que lista todos os tipos de combustivel
    public List<TipoCombustivel> listarTodos(){
        List<TipoCombustivel> tipos = repository.findAll();

        if (tipos.isEmpty()) {
            throw new NenhumRegistroEncontradoException("Nenhum tipo de combustível cadastrado");
        }

        return tipos;
    }

    // Lógica que lista tipo de combustivel por ID
    public Optional<TipoCombustivel> listarPorId(Long id){
        Optional<TipoCombustivel> tipo = repository.findById(id);
        if (tipo.isEmpty()) {
            throw new NenhumRegistroEncontradoException("Tipo de combustível com ID " + id + " não encontrado");
        }
        return tipo;
    }

    // UPDATE SERVICE - TIPO DE COMBUSTIVEL
    // Lógica que atualiza os dados de um registro tipo de combustivel por ID
    public TipoCombustivel atualizar(TipoCombustivel tipo){
        if (tipo.getId() == null) {
            throw new DadosInvalidosException("ID é obrigatório para atualização");
        }
        return repository.save(tipo);
    }

    // DELETE SERVICE - TIPO DE COMBUSTIVEL
    // Lógica que apaga um registro tipo de combustivel por ID
    public void deletar(Long id){
        if (!repository.existsById(id)) {
            throw new NenhumRegistroEncontradoException("Não foi possível excluir: ID " + id + " não existe");
        }
        repository.deleteById(id);
    }
}
