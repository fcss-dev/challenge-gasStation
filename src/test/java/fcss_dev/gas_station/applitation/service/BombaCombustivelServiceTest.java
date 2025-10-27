package fcss_dev.gas_station.applitation.service;

import fcss_dev.gas_station.applitation.exceptions.NenhumRegistroEncontradoException;
import fcss_dev.gas_station.applitation.model.BombaCombustivel;
import fcss_dev.gas_station.applitation.model.TipoCombustivel;
import fcss_dev.gas_station.applitation.repository.BombaCombustivelRepository;
import fcss_dev.gas_station.applitation.repository.TipoCombustivelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BombaCombustivelServiceTest {

    @Mock
    private BombaCombustivelRepository bombaCombustivelRepository;

    @Mock
    private TipoCombustivelRepository tipoCombustivelRepository;

    @InjectMocks
    private BombaCombustivelService bombaService;

    private BombaCombustivel bomba;
    private TipoCombustivel tipo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        tipo = new TipoCombustivel();
        tipo.setId(1L);
        tipo.setNome("Gasolina");

        bomba = new BombaCombustivel();
        bomba.setNome("Bomba 1");
        bomba.setTipoCombustivel(tipo);
    }

    // CREATE SERVICE TESTE - BOMBA COMBUSTIVEL
    @Test
    void criar_Sucesso() {
        when(bombaCombustivelRepository.existsByNome(bomba.getNome())).thenReturn(false);
        when(tipoCombustivelRepository.findById(tipo.getId())).thenReturn(Optional.of(tipo));
        when(bombaCombustivelRepository.save(bomba)).thenReturn(bomba);

        BombaCombustivel resultado = bombaService.criar(bomba);

        assertNotNull(resultado);
        assertEquals(bomba.getNome(), resultado.getNome());
        assertEquals(tipo, resultado.getTipoCombustivel());

        verify(bombaCombustivelRepository).save(bomba);
    }

    @Test
    void criar_Falha_NomeExistente() {
        when(bombaCombustivelRepository.existsByNome(bomba.getNome())).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            bombaService.criar(bomba);
        });

        assertEquals("Já existe uma bomba com o nome informado.", exception.getMessage());
        verify(bombaCombustivelRepository, never()).save(any());
    }

    @Test
    void criar_Falha_TipoNaoEncontrado() {
        when(bombaCombustivelRepository.existsByNome(bomba.getNome())).thenReturn(false);
        when(tipoCombustivelRepository.findById(tipo.getId())).thenReturn(Optional.empty());

        NenhumRegistroEncontradoException exception = assertThrows(NenhumRegistroEncontradoException.class, () -> {
            bombaService.criar(bomba);
        });

        assertEquals("Tipo de combustível não encontrado.", exception.getMessage());
        verify(bombaCombustivelRepository, never()).save(any());
    }

    // READ SERVICE TESTE - BOMBA COMBUSTIVEL
    @Test
    void listarTodos_quandoExistiremRegistros() {
        BombaCombustivel bomba1 = new BombaCombustivel();
        bomba1.setId(1L);
        bomba1.setNome("Bomba 1");

        BombaCombustivel bomba2 = new BombaCombustivel();
        bomba2.setId(2L);
        bomba2.setNome("Bomba 2");

        when(bombaCombustivelRepository.findAll()).thenReturn(List.of(bomba1, bomba2));

        List<BombaCombustivel> resultado = bombaService.listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Bomba 1", resultado.get(0).getNome());
        verify(bombaCombustivelRepository, times(1)).findAll();
    }

    @Test
    void listarTodos_excecaoQuandoNaoHouverBombas() {
        when(bombaCombustivelRepository.findAll()).thenReturn(Collections.emptyList());

        NenhumRegistroEncontradoException exception = assertThrows(
                NenhumRegistroEncontradoException.class,
                () -> bombaService.listarTodos()
        );

        assertEquals("Nenhuma bomba de combustível encontrada.", exception.getMessage());
        verify(bombaCombustivelRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId_quandoIdExistir() {
        Long id = 1L;
        BombaCombustivel bomba = new BombaCombustivel();
        bomba.setId(id);
        bomba.setNome("Bomba Teste");

        when(bombaCombustivelRepository.findById(id)).thenReturn(Optional.of(bomba));

        BombaCombustivel resultado = bombaService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals("Bomba Teste", resultado.getNome());
        verify(bombaCombustivelRepository, times(1)).findById(id);
    }

    @Test
    void buscarPorId_excecaoQuandoIdForNulo() {
        NenhumRegistroEncontradoException exception = assertThrows(
                NenhumRegistroEncontradoException.class,
                () -> bombaService.buscarPorId(null)
        );

        assertEquals("ID inválido. O ID deve ser um número positivo.", exception.getMessage());
        verify(bombaCombustivelRepository, never()).findById(any());
    }

    @Test
    void buscarPorId_excecaoQuandoIdForMenorOuIgualZero() {
        NenhumRegistroEncontradoException exception = assertThrows(
                NenhumRegistroEncontradoException.class,
                () -> bombaService.buscarPorId(0L)
        );

        assertEquals("ID inválido. O ID deve ser um número positivo.", exception.getMessage());
        verify(bombaCombustivelRepository, never()).findById(any());
    }

    @Test
    void buscarPorId_excecaoQuandoBombaNaoExistir() {
        Long id = 99L;
        when(bombaCombustivelRepository.findById(id)).thenReturn(Optional.empty());

        NenhumRegistroEncontradoException exception = assertThrows(
                NenhumRegistroEncontradoException.class,
                () -> bombaService.buscarPorId(id)
        );

        assertEquals("Bomba de combustível não encontrada com ID: 99", exception.getMessage());
        verify(bombaCombustivelRepository, times(1)).findById(id);
    }

    // UPDATE SERVICE TESTE - BOMBA COMBUSTIVEL
    // DELETE SERVICE TESTE - BOMBA COMBUSTIVEL
}