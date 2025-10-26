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
    // UPDATE SERVICE TESTE - BOMBA COMBUSTIVEL
    // DELETE SERVICE TESTE - BOMBA COMBUSTIVEL
}