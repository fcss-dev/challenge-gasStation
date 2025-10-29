package fcss_dev.gas_station.applitation.service;

import fcss_dev.gas_station.applitation.exceptions.NenhumRegistroEncontradoException;
import fcss_dev.gas_station.applitation.model.BombaCombustivel;
import fcss_dev.gas_station.applitation.repository.BombaCombustivelRepository;
import fcss_dev.gas_station.applitation.repository.TipoCombustivelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BombaCombustivelService {

    @Autowired
    private BombaCombustivelRepository bombaRepository;

    @Autowired
    private TipoCombustivelRepository tipoCombustivelRepository;


    // CREATE SERVICE - BOMBA COMBUSTIVEL
    // Lógica que adiciona uma nova bomba de combustivel ao banco
    public BombaCombustivel criar(BombaCombustivel bombaCombustivel) {
        if (bombaRepository.existsByNome(bombaCombustivel.getNome())) {
            throw new IllegalArgumentException("Já existe uma bomba com o nome informado.");
        }

        var tipo = tipoCombustivelRepository.findById(bombaCombustivel.getTipoCombustivel().getId())
                .orElseThrow(() -> new NenhumRegistroEncontradoException("Tipo de combustível não encontrado."));

        bombaCombustivel.setTipoCombustivel(tipo);
        return bombaRepository.save(bombaCombustivel);
    }

    // READ SERVICE - BOMBA COMBUSTIVEL
    // Lógica que lista todas as bombas de combustivel registradas no banco
    public List<BombaCombustivel> listarTodos() {
        List<BombaCombustivel> bombas = bombaRepository.findAll();
        if (bombas.isEmpty()) {
            throw new NenhumRegistroEncontradoException("Nenhuma bomba de combustível encontrada.");
        }
        return bombas;
    }

    // Lógica que lista bomba de combustivel por ID
    public BombaCombustivel buscarPorId(Long id) {
        if (id == null || id <= 0) {
            throw new NenhumRegistroEncontradoException("ID inválido. O ID deve ser um número positivo.");
        }
        return bombaRepository.findById(id).orElseThrow(() -> new NenhumRegistroEncontradoException("Bomba de combustível não encontrada com ID: " + id));
    }

    // UPDATE SERVICE - BOMBA COMBUSTIVEL
    // Lógica que atualiza os dados da bomba de combustivel por ID
    public BombaCombustivel atualizar(Long id, BombaCombustivel bombaAtualizada) {
        BombaCombustivel existente = buscarPorId(id);
        existente.setNome(bombaAtualizada.getNome());
        var tipo = tipoCombustivelRepository.findById(bombaAtualizada.getTipoCombustivel().getId())
                .orElseThrow(() -> new NenhumRegistroEncontradoException("Tipo de combustível não encontrado."));
        existente.setTipoCombustivel(tipo);

        return bombaRepository.save(existente);
    }

    // DELETE SERVICE - BOMBA COMBUSTIVEL
    // Lógica que apaga registro de bomba de combustivel do banco
    public void deletar(Long id) {
        if (!bombaRepository.existsById(id)) {
            throw new NenhumRegistroEncontradoException("Bomba de combustível não encontrada para exclusão.");
        }
        bombaRepository.deleteById(id);
    }
}
