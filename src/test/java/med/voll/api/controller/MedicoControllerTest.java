package med.voll.api.controller;

import med.voll.api.domain.medico.MedicoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
class MedicoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MedicoRepository medicoRepository;
    @Test
    @WithMockUser
    @DisplayName("Deveria retornar código http 201 quando os dados estão corretos")
    void cadastrarCenario1() throws Exception {
        String jsonPostRequest = """
                   {
                     "nome": "string",
                     "email": "string@email.com",
                     "telefone": "string",
                     "crm": "4078",
                     "especialidade": "ORTOPEDIA",
                     "endereco": {
                       "logradouro": "string",
                       "bairro": "string",
                       "cep": "14288387",
                       "cidade": "string",
                       "uf": "string",
                       "complemento": "string",
                       "numero": "string"
                     }
                   }
                """;
        MockHttpServletResponse response;
        response = mockMvc.perform(post("/medicos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPostRequest)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @WithMockUser
    @DisplayName("Deveria retornar código http 400 quando os dados estão corretos")
    void cadastrarCenario2() throws Exception {
        String jsonPostRequest = """
                   {
                     "nome": null,
                     "email": "string@email.com",
                     "telefone": "string",
                     "crm": "XXXXX",
                     "especialidade": "ORTOPEDIA",
                     "endereco": {
                       "logradouro": "string",
                       "bairro": "string",
                       "cep": "14288387",
                       "cidade": "string",
                       "uf": "string",
                       "complemento": "string",
                       "numero": "string"
                     }
                   }
                """;
        MockHttpServletResponse response;
        response = mockMvc.perform(post("/medicos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPostRequest)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}