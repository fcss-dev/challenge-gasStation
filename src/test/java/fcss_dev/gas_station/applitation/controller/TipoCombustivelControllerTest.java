package fcss_dev.gas_station.applitation.controller;

import fcss_dev.gas_station.applitation.exceptions.DadosInvalidosException;
import fcss_dev.gas_station.applitation.exceptions.NenhumRegistroEncontradoException;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.reflect.Array.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class TipoCombustivelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private TipoCombustivelController controller;

    @MockBean
    private TipoCombustivelService service;

    private TipoCombustivel tipo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tipo = new TipoCombustivel();
        tipo.setId(1L);
        tipo.setNome("Gasolina");
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
    @Test
    void listarTodos_listaTiposQuandoExistem(){
        TipoCombustivel tipo1 = new TipoCombustivel(1L, "Gasolina");
        TipoCombustivel tipo2 = new TipoCombustivel(2L, "Etanol");
        when(service.listarTodos()).thenReturn(Arrays.asList(tipo1, tipo2));

        ResponseEntity<?> resposta = controller.listarTodos();

        assertEquals(200, resposta.getStatusCodeValue());
        List<TipoCombustivel> tipos = (List<TipoCombustivel>) resposta.getBody();
        assertNotNull(tipos);
        assertEquals(2, tipos.size());
        verify(service, times(1)).listarTodos();
    }

    @Test
    void listarTodos_retornar404NaoHouverRegistros(){
        when(service.listarTodos()).thenThrow(new NenhumRegistroEncontradoException("Nenhum tipo de combustível cadastrado"));

        ResponseEntity<?> resposta = controller.listarTodos();

        assertEquals(404, resposta.getStatusCodeValue());
        assertEquals("Nenhum tipo de combustível cadastrado", resposta.getBody());
    }

    @Test
    void listarPorId_okQuandoIdExistente() {
        Long id = 1L;
        TipoCombustivel tipo = new TipoCombustivel();
        tipo.setId(id);

        when(service.listarPorId(id)).thenReturn(Optional.of(tipo));

        ResponseEntity<?> resposta = controller.listarPorId(id);

        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(Optional.of(tipo), resposta.getBody()); // <-- compara com Optional
        verify(service, times(1)).listarPorId(id);
    }

    @Test
    void listarPorId_404QuandoNaoEncontrarRegistro() {
        Long id = 99L;
        when(service.listarPorId(id))
                .thenThrow(new NenhumRegistroEncontradoException("Registro não encontrado"));

        ResponseEntity<?> resposta = controller.listarPorId(id);

        assertEquals(404, resposta.getStatusCodeValue());
        assertEquals("Registro não encontrado", resposta.getBody());
        verify(service, times(1)).listarPorId(id);
    }

    // Update
    @Test
    void atualizar_comSucesso() {
        when(service.atualizar(tipo)).thenReturn(tipo);

        ResponseEntity<?> resposta = controller.atualizar(1L, tipo);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(tipo, resposta.getBody());
        verify(service, times(1)).atualizar(tipo);
    }

    @Test
    void atualizar_BadRequestQuandoIdForNulo() {
        tipo.setId(null);
        when(service.atualizar(tipo)).thenThrow(new DadosInvalidosException("ID é obrigatório para atualização"));

        ResponseEntity<?> resposta = controller.atualizar(1L, tipo);

        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        assertTrue(((Map<?, ?>) resposta.getBody()).get("erro").toString().contains("ID é obrigatório"));
        verify(service, times(1)).atualizar(tipo);
    }

    @Test
    void atualizar_internalServerErrorQuandoOcorreErroInesperado() {
        when(service.atualizar(tipo)).thenThrow(new RuntimeException("Erro inesperado"));

        ResponseEntity<?> resposta = controller.atualizar(1L, tipo);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, resposta.getStatusCode());
        assertTrue(((Map<?, ?>) resposta.getBody()).get("erro").toString().contains("Erro inesperado"));
        verify(service, times(1)).atualizar(tipo);
    }

    // Delete
}