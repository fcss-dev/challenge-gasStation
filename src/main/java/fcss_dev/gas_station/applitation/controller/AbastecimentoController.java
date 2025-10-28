package fcss_dev.gas_station.applitation.controller;

import fcss_dev.gas_station.applitation.model.Abastecimento;
import fcss_dev.gas_station.applitation.service.AbastecimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/abastecimentos")
public class AbastecimentoController {

    private final AbastecimentoService abastecimentoService;


    // CREATE CONTROLLER - ABASTECIMENTOS
    @PostMapping
    public ResponseEntity<Abastecimento> salvarRegistro(@RequestBody Abastecimento abastecimento) {
        return ResponseEntity.ok(abastecimentoService.salvarRegistro(abastecimento));
    }

    // READ CONTROLLER - ABASTECIMENTOS

    // UPDATE CONTROLLER - ABASTECIMENTOS

    // DELETE CONTROLLER - ABASTECIMENTOS
}
