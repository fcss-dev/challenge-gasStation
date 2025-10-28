package fcss_dev.gas_station.applitation.controller;

import fcss_dev.gas_station.applitation.model.Abastecimento;
import fcss_dev.gas_station.applitation.service.AbastecimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/abastecimentos")
public class AbastecimentoController {

    private final AbastecimentoService abastecimentoService;


    // CREATE CONTROLLER - ABASTECIMENTOS
    // Rota que adiciona um novo registro de abastecimento ao banco
    @PostMapping
    public ResponseEntity<Abastecimento> salvarRegistro(@RequestBody Abastecimento abastecimento) {
        return ResponseEntity.ok(abastecimentoService.salvarRegistro(abastecimento));
    }

    // READ CONTROLLER - ABASTECIMENTOS
    // Rota que lista todos o historico de abastecimentos
    @GetMapping
    public ResponseEntity<List<Abastecimento>> listarTodosRegistros() {
        return ResponseEntity.ok(abastecimentoService.listarTodosRegistros());
    }

    // Rota que lista registro abastecimento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Abastecimento> buscarRegistroPorId(@PathVariable Long id) {
        return ResponseEntity.ok(abastecimentoService.buscarRegistroPorId(id));
    }

    // UPDATE CONTROLLER - ABASTECIMENTOS
    // Rota que atualiza os dados de um registro de abastecimento por ID
    @PutMapping("/{id}")
    public ResponseEntity<Abastecimento> atualizarRegistros(@PathVariable Long id, @RequestBody Abastecimento abastecimento) {
        return ResponseEntity.ok(abastecimentoService.atualizarRegistros(id, abastecimento));
    }

    // DELETE CONTROLLER - ABASTECIMENTOS
    // Rota que apaga um registro de abastecimento por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRegistrosPorId(@PathVariable Long id) {
        abastecimentoService.deletarRegistrosPorId(id);
        return ResponseEntity.noContent().build();
    }

}
