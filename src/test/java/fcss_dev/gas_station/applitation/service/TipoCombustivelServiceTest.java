package fcss_dev.gas_station.applitation.service;

import fcss_dev.gas_station.applitation.exceptions.DadosInvalidosException;
import fcss_dev.gas_station.applitation.exceptions.NenhumRegistroEncontradoException;
import fcss_dev.gas_station.applitation.model.TipoCombustivel;
import fcss_dev.gas_station.applitation.repository.TipoCombustivelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TipoCombustivelServiceTest {

    @Mock
    private TipoCombustivelRepository repository;

    @InjectMocks TipoCombustivelService service;

    public TipoCombustivelServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Create
    @Test
    void salvarTest_LancarExcecaoQuandoDadosInvalidos() {
        TipoCombustivel tipo = new TipoCombustivel();

        assertThrows(DadosInvalidosException.class, () -> {
            service.salvar(tipo);
        });
    }

    @Test
    void salvarTest_quandoDadosValidos() {
        TipoCombustivel tipo = new TipoCombustivel();
        tipo.setNome("Gasolina");
        tipo.setPrecoPorLitro(java.math.BigDecimal.valueOf(5.50));

        when(repository.save(tipo)).thenReturn(tipo);

        var salvo = service.salvar(tipo);

        verify(repository).save(tipo);
    }

    // Read
    @Test
    void listarTodosTest_lancarExcecaoQuandoNaoHouverRegistros() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(NenhumRegistroEncontradoException.class, () -> {
            service.listarTodos();
        });
    }

    @Test
    void listarPorIdTest_LancarExcecaoQuandoIdNaoEncontrado() {
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NenhumRegistroEncontradoException.class, () -> {
            service.listarPorId(id);
        });
    }

    @Test
    void listarPorIdTest_RetornarQuandoIdExiste() {
        Long id = 1L;
        TipoCombustivel tipo = new TipoCombustivel();
        tipo.setId(id);
        tipo.setNome("Gasolina");
        tipo.setPrecoPorLitro(java.math.BigDecimal.valueOf(5.50));

        when(repository.findById(id)).thenReturn(Optional.of(tipo));

        Optional<TipoCombustivel> resultado = service.listarPorId(id);

        assert(resultado.isPresent());
    }

    // Update


    @Test
    void atualizarTest_LancarExcecaoQuandoIdForNulo() {
        TipoCombustivel tipo = new TipoCombustivel();
        tipo.setNome("Gasolina");
        tipo.setPrecoPorLitro(BigDecimal.valueOf(5.50));

        assertThrows(DadosInvalidosException.class, () -> {
            service.atualizar(tipo);
        });
    }

    @Test
    void atualizarTest_atualizarQuandoIdForValido() {
        TipoCombustivel tipo = new TipoCombustivel();
        tipo.setId(1L);
        tipo.setNome("Gasolina");
        tipo.setPrecoPorLitro(BigDecimal.valueOf(5.50));

        when(repository.save(tipo)).thenReturn(tipo);

        TipoCombustivel atualizado = service.atualizar(tipo);

        verify(repository).save(tipo);

        assertEquals("Gasolina", atualizado.getNome());
        assertEquals(BigDecimal.valueOf(5.50), atualizado.getPrecoPorLitro());
    }

    // Delete
    @Test
    void deletar() {
    }
}