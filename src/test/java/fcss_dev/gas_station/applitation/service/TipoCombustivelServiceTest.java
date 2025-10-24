package fcss_dev.gas_station.applitation.service;

import fcss_dev.gas_station.applitation.model.TipoCombustivel;
import fcss_dev.gas_station.applitation.repository.TipoCombustivelRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

    @Test
    void salvarTest_LancarExcecaoQuandoDadosInvalidos() {
        TipoCombustivel tipo = new TipoCombustivel();

        assertThrows(IllegalArgumentException.class, () -> {
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

    @Test
    void listarTodos() {
    }

    @Test
    void listarPorId() {
    }

    @Test
    void atualizar() {
    }

    @Test
    void deletar() {
    }
}