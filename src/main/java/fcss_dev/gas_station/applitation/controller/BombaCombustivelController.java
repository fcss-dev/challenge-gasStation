package fcss_dev.gas_station.applitation.controller;

import fcss_dev.gas_station.applitation.exceptions.NenhumRegistroEncontradoException;
import fcss_dev.gas_station.applitation.model.BombaCombustivel;
import fcss_dev.gas_station.applitation.service.BombaCombustivelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bombas_combustivel")
public class BombaCombustivelController {

    @Autowired
    private BombaCombustivelService service;

    private final BombaCombustivelService bombaCombustivelService;

    // CREATE CONTROLLER - BOMBA COMBUSTIVEL
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody BombaCombustivel bombaCombustivel) {
        try {
            BombaCombustivel novaBomba = service.criar(bombaCombustivel);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaBomba);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NenhumRegistroEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar bomba de combustível: " + e.getMessage());
        }
    }

    // READ CONTROLLER - BOMBA COMBUSTIVEL
    @GetMapping
    public ResponseEntity<?> listarTodos() {
        try {
            List<BombaCombustivel> bombas = bombaCombustivelService.listarTodos();
            return ResponseEntity.ok(bombas);
        } catch (NenhumRegistroEncontradoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao listar bombas: " + ex.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            BombaCombustivel bomba = bombaCombustivelService.buscarPorId(id);
            return ResponseEntity.ok(bomba);
        } catch (NenhumRegistroEncontradoException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno ao buscar bomba de combustível.");
        }
    }


    // UPDATE CONTROLLER - BOMBA COMBUSTIVEL
    // DELETE CONTROLLER - BOMBA COMBUSTIVEL
}
