package fcss_dev.gas_station.applitation.controller;

import fcss_dev.gas_station.applitation.exceptions.DadosInvalidosException;
import fcss_dev.gas_station.applitation.exceptions.NenhumRegistroEncontradoException;
import fcss_dev.gas_station.applitation.exceptions.NomeJaExisteException;
import fcss_dev.gas_station.applitation.model.TipoCombustivel;
import fcss_dev.gas_station.applitation.service.TipoCombustivelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tipos_combustivel")
public class TipoCombustivelController {

    @Autowired
    private TipoCombustivelService service;

    // Create
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody TipoCombustivel tipo){
        try {
            TipoCombustivel novo = service.salvar(tipo);
            return ResponseEntity.status(HttpStatus.CREATED).body(novo);
        } catch (NomeJaExisteException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("erro", e.getMessage()));
        } catch (DadosInvalidosException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("erro", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("erro", "Erro inesperado ao criar tipo de combustível."));
        }
    }

    // Read
    @GetMapping
    public ResponseEntity<?> listarTodos(){
        try {
            List<TipoCombustivel> tipos = service.listarTodos();
            return ResponseEntity.ok(tipos);
        } catch (NenhumRegistroEncontradoException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id){
        try {
            return ResponseEntity.ok(service.listarPorId(id));
        } catch ( NenhumRegistroEncontradoException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody TipoCombustivel tipo){
        try {
            tipo.setId(id);

            TipoCombustivel atualizado = service.atualizar(tipo);
            return ResponseEntity.ok(atualizado);
        } catch (DadosInvalidosException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("erro", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("erro", "Erro inesperado ao atualizar o tipo de combustível"));
        }
    }

    // Delete


}
