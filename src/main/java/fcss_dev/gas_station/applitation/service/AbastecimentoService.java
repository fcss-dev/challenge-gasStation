package fcss_dev.gas_station.applitation.service;

import fcss_dev.gas_station.applitation.model.Abastecimento;
import fcss_dev.gas_station.applitation.repository.AbastecimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AbastecimentoService {
    private final AbastecimentoRepository abastecimentoRepository;

    // CREATE SERVICE - ABASTECIMENTOS
    public Abastecimento salvarRegistro(Abastecimento abastecimento){
        return abastecimentoRepository.save(abastecimento);
    }

    // READ SERVICE - ABASTECIMENTOS

    // UPDATE SERVICE - ABASTECIMENTOS

    // DELETE SERVICE - ABASTECIMENTOS

}
