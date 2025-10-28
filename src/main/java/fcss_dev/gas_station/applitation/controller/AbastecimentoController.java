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
    @PostMapping
    public ResponseEntity<Abastecimento> salvarRegistro(@RequestBody Abastecimento abastecimento) {
        return ResponseEntity.ok(abastecimentoService.salvarRegistro(abastecimento));
    }

    // READ CONTROLLER - ABASTECIMENTOS
    @GetMapping
    public ResponseEntity<List<Abastecimento>> listarTodosRegistros() {
        return ResponseEntity.ok(abastecimentoService.listarTodosRegistros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Abastecimento> buscarRegistroPorId(@PathVariable Long id) {
        return ResponseEntity.ok(abastecimentoService.buscarRegistroPorId(id));
    }

    // UPDATE CONTROLLER - ABASTECIMENTOS
    @PutMapping("/{id}")
    public ResponseEntity<Abastecimento> atualizarRegistros(@PathVariable Long id, @RequestBody Abastecimento abastecimento) {
        return ResponseEntity.ok(abastecimentoService.atualizarRegistros(id, abastecimento));
    }

    // DELETE CONTROLLER - ABASTECIMENTOS
}
