package fcss_dev.gas_station.applitation.controller;

import fcss_dev.gas_station.applitation.exceptions.NenhumRegistroEncontradoException;
import fcss_dev.gas_station.applitation.model.BombaCombustivel;
import fcss_dev.gas_station.applitation.service.BombaCombustivelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;

class BombaCombustivelControllerTest {

    @Mock
    private BombaCombustivelService service;

    @InjectMocks
    private BombaCombustivelController controller;

    private BombaCombustivel bomba;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bomba = new BombaCombustivel();
        bomba.setId(1L);
        bomba.setNome("Bomba A");
    }

    // CREATE CONTROLLER TESTE - BOMBA COMBUSTIVEL
    @Test
    public void criar_Sucesso() {
        when(service.criar(bomba)).thenReturn(bomba);

        ResponseEntity<?> response = controller.criar(bomba);

        assertEquals(CREATED, response.getStatusCode());
        assertEquals(bomba, response.getBody());
    }

    @Test
    public void criar_IllegalArgumentException() {
        when(service.criar(bomba)).thenThrow(new IllegalArgumentException("Nome já existe"));

        ResponseEntity<?> response = controller.criar(bomba);

        assertEquals(BAD_REQUEST, response.getStatusCode());
        assertEquals("Nome já existe", response.getBody());
    }

    @Test
    public void criar_NenhumRegistroEncontradoException() {
        when(service.criar(bomba)).thenThrow(new NenhumRegistroEncontradoException("Tipo de combustível não encontrado"));

        ResponseEntity<?> response = controller.criar(bomba);

        assertEquals(NOT_FOUND, response.getStatusCode());
        assertEquals("Tipo de combustível não encontrado", response.getBody());
    }

    @Test
    public void criar_ExceptionGenerica() {
        when(service.criar(bomba)).thenThrow(new RuntimeException("Erro inesperado"));

        ResponseEntity<?> response = controller.criar(bomba);

        assertEquals(INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Erro ao criar bomba de combustível: Erro inesperado"));
    }

    // READ CONTROLLER TESTE - BOMBA COMBUSTIVEL
    // UPDATE CONTROLLER TESTE - BOMBA COMBUSTIVEL
    // DELETE CONTROLLER TESTE - BOMBA COMBUSTIVEL
}