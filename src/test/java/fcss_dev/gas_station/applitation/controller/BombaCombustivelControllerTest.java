package fcss_dev.gas_station.applitation.controller;

import fcss_dev.gas_station.applitation.exceptions.NenhumRegistroEncontradoException;
import fcss_dev.gas_station.applitation.model.BombaCombustivel;
import fcss_dev.gas_station.applitation.service.BombaCombustivelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
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
    @Test
    void listarTodos_quandoExistirem() {
        List<BombaCombustivel> bombas = List.of(
                new BombaCombustivel(1L, "Bomba A"),
                new BombaCombustivel(2L, "Bomba B")
        );
        when(service.listarTodos()).thenReturn(bombas);

        ResponseEntity<?> resposta = controller.listarTodos();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(bombas, resposta.getBody());
        verify(service, times(1)).listarTodos();
    }

    @Test
    void listarTodos_404quandoNaoExistiremBombas() {
        when(service.listarTodos())
                .thenThrow(new NenhumRegistroEncontradoException("Nenhuma bomba de combustível encontrada."));

        ResponseEntity<?> resposta = controller.listarTodos();

        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
        assertEquals("Nenhuma bomba de combustível encontrada.", resposta.getBody());
        verify(service, times(1)).listarTodos();
    }

    @Test
    void listarTodos_500quandoOcorrerErroInesperado() {
        when(service.listarTodos())
                .thenThrow(new RuntimeException("Erro inesperado no banco."));

        ResponseEntity<?> resposta = controller.listarTodos();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, resposta.getStatusCode());
        assertTrue(((String) resposta.getBody()).contains("Erro ao listar bombas: Erro inesperado no banco."));
        verify(service, times(1)).listarTodos();
    }

    @Test
    void buscarPorId_sucesso() {
        BombaCombustivel bomba = new BombaCombustivel();
        bomba.setId(1L);
        bomba.setNome("Bomba A");

        when(service.buscarPorId(1L)).thenReturn(bomba);

        ResponseEntity<?> response = controller.buscarPorId(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(bomba, response.getBody());
    }

    @Test
    void buscarPorId_nenhumRegistro() {
        when(service.buscarPorId(2L)).thenThrow(new NenhumRegistroEncontradoException("Bomba não encontrada"));

        ResponseEntity<?> response = controller.buscarPorId(2L);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Bomba não encontrada", response.getBody());
    }

    @Test
    void buscarPorId_erroInterno() {
        when(service.buscarPorId(3L))
                .thenThrow(new RuntimeException("Erro inesperado"));

        ResponseEntity<?> response = controller.buscarPorId(3L);

        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Erro interno ao buscar bomba de combustível.", response.getBody());
    }


    // UPDATE CONTROLLER TESTE - BOMBA COMBUSTIVEL
    // DELETE CONTROLLER TESTE - BOMBA COMBUSTIVEL
}