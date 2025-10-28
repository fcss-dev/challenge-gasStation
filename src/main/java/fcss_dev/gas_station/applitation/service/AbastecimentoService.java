package fcss_dev.gas_station.applitation.service;

import fcss_dev.gas_station.applitation.model.Abastecimento;
import fcss_dev.gas_station.applitation.repository.AbastecimentoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AbastecimentoService {
    private final AbastecimentoRepository abastecimentoRepository;

    // CREATE SERVICE - ABASTECIMENTOS
    public Abastecimento salvarRegistro(Abastecimento abastecimento){
        return abastecimentoRepository.save(abastecimento);
    }

    // READ SERVICE - ABASTECIMENTOS
    public List<Abastecimento> listarTodosRegistros(){
        return abastecimentoRepository.findAll();
    }
    public Abastecimento buscarRegistroPorId(Long id){
        return abastecimentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Abastecimento não encontrado com ID: " + id));
    }

    // UPDATE SERVICE - ABASTECIMENTOS
    public Abastecimento atualizarRegistros(Long id, Abastecimento abastecimentoAtualizado) {
        Abastecimento existente = buscarRegistroPorId(id);

        existente.setBomba(abastecimentoAtualizado.getBomba());
        existente.setDataAbastecimento(abastecimentoAtualizado.getDataAbastecimento());
        existente.setLitrosAbastecidos(abastecimentoAtualizado.getLitrosAbastecidos());
        existente.setValorTotal(abastecimentoAtualizado.getValorTotal());

        return abastecimentoRepository.save(existente);
    }

    // DELETE SERVICE - ABASTECIMENTOS
    public void deletarRegistrosPorId(Long id) {
        Abastecimento existente = buscarRegistroPorId(id);
        abastecimentoRepository.delete(existente);
    }

}
