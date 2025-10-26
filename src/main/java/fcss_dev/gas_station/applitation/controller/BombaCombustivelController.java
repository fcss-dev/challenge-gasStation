package fcss_dev.gas_station.applitation.controller;

import fcss_dev.gas_station.applitation.exceptions.NenhumRegistroEncontradoException;
import fcss_dev.gas_station.applitation.model.BombaCombustivel;
import fcss_dev.gas_station.applitation.service.BombaCombustivelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bombas_combustivel")
public class BombaCombustivelController {

    @Autowired
    private BombaCombustivelService service;

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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar bomba de combustível: " + e.getMessage());
        }
    }

    // READ CONTROLLER - BOMBA COMBUSTIVEL
    // UPDATE CONTROLLER - BOMBA COMBUSTIVEL
    // DELETE CONTROLLER - BOMBA COMBUSTIVEL
}
