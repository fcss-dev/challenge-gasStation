package fcss_dev.gas_station.applitation.controller;

import fcss_dev.gas_station.applitation.exceptions.DadosInvalidosException;
import fcss_dev.gas_station.applitation.model.TipoCombustivel;
import fcss_dev.gas_station.applitation.service.TipoCombustivelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import java.math.BigDecimal;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
class TipoCombustivelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private TipoCombustivelController controller;

    @MockBean
    private TipoCombustivelService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    // Create
    @Test
    void criarTeste_sucessoAoCriar() throws Exception {
        TipoCombustivel tipo = new TipoCombustivel();
        tipo.setId(1L);
        tipo.setNome("Gasolina");
        tipo.setPrecoPorLitro(BigDecimal.valueOf(5.50));

        when(service.salvar(Mockito.any(TipoCombustivel.class)))
                .thenReturn(tipo);

        mockMvc.perform(post("/api/tipos_combustivel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "nome": "Gasolina",
                            "precoPorLitro": 5.5
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Gasolina"))
                .andExpect(jsonPath("$.precoPorLitro").value(5.5));
    }

    @Test
    void criarTeste_dadosInvalidos() throws Exception {
        TipoCombustivel tipo = new TipoCombustivel();
        tipo.setNome("");

        when(service.salvar(tipo)).thenThrow(new DadosInvalidosException("Nome inválido"));

        ResponseEntity<?> response = controller.criar(tipo);

        assertEquals(BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertInstanceOf(Map.class, response.getBody());

        Map<?, ?> body = (Map<?, ?>) response.getBody();
        assertEquals("Nome inválido", body.get("erro"));
    }

    @Test
    void criarTeste_erroInesperado() throws Exception {
        TipoCombustivel tipo = new TipoCombustivel();
        tipo.setNome("Gasolina");

        when(service.salvar(tipo)).thenThrow(new RuntimeException("Erro de banco"));

        ResponseEntity<?> response = controller.criar(tipo);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertInstanceOf(Map.class, response.getBody());

        Map<?, ?> body = (Map<?, ?>) response.getBody();
        assertEquals("Erro inesperado ao criar tipo de combustível.", body.get("erro"));
    }

    // Read

    // Update

    // Delete
}