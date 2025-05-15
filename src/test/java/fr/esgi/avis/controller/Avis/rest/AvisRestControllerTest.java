package fr.esgi.avis.controller.Avis.rest;

import com.github.javafaker.Faker;
import fr.esgi.avis.controller.Avis.AvisController;
import fr.esgi.avis.controller.Avis.dto.AvisDTO;
import fr.esgi.avis.controller.Joueur.dto.JoueurDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class AvisRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AvisController avisController;

    private Faker faker;
    private AvisDTO avisDTO;

    @BeforeEach
    void setUp() {
        faker = new Faker();
        avisDTO = new AvisDTO();
        avisDTO.setId(1L);
        avisDTO.setDescription(faker.lorem().sentence());
        avisDTO.setNote(4.5f);
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldCreateAvisSuccessfully() throws Exception {
        // GIVEN
        // Préparation du joueur mocké dans la session
        MockHttpSession session = new MockHttpSession();
        JoueurDTO joueurMock = new JoueurDTO();
        joueurMock.setId(42L);
        session.setAttribute("utilisateur", joueurMock);

        // Préparation du DTO simulé en retour du controller
        avisDTO.setJeuId(1L);         // Nécessaire pour valider la création
        avisDTO.setJoueurId(42L);     // Aligné avec l'utilisateur simulé
        avisDTO.setDateDEnvoi(LocalDateTime.now());
        avisDTO.setModerateurId(1L);

        when(avisController.createAvis(Mockito.any(AvisDTO.class))).thenReturn(avisDTO);

        String jsonBody = String.format(
                "{" +
                "\"description\":\"%s\"," +
                "\"note\":4.5," +
                "\"jeuId\":1" +
                "}", avisDTO.getDescription()
        );

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/avis")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody)
                .accept(MediaType.APPLICATION_JSON)
                .session(session); // injection de la session mockée

        // WHEN & THEN
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated()) // car createAvis retourne un CREATED
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(avisDTO.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.note").value(4.5))
                .andDo(MockMvcResultHandlers.print());

        verify(avisController, times(1)).createAvis(Mockito.any(AvisDTO.class));
    }


    @Test
    @WithMockUser(roles = "USER")
    void shouldGetAvisByIdSuccessfully() throws Exception {
        // GIVEN
        when(avisController.getAvisById(1L)).thenReturn(Optional.of(avisDTO));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/avis/1")
                .accept(MediaType.APPLICATION_JSON);

        // WHEN & THEN
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(avisDTO.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.note").value(4.5))
                .andDo(MockMvcResultHandlers.print());

        verify(avisController, times(1)).getAvisById(1L);
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldReturnNotFoundWhenAvisDoesNotExist() throws Exception {
        // GIVEN
        when(avisController.getAvisById(2L)).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/avis/2")
                .accept(MediaType.APPLICATION_JSON);

        // WHEN & THEN
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

        verify(avisController, times(1)).getAvisById(2L);
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldDeleteAvisSuccessfully() throws Exception {
        // GIVEN
        doNothing().when(avisController).deleteAvis(1L);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/avis/1")
                .accept(MediaType.APPLICATION_JSON);

        // WHEN & THEN
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());

        verify(avisController, times(1)).deleteAvis(1L);
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldReturnBadRequestWhenValidationFails() throws Exception {
        MockHttpSession session = new MockHttpSession();
        JoueurDTO joueurMock = new JoueurDTO();
        joueurMock.setId(42L);
        session.setAttribute("utilisateur", joueurMock);

        String invalidBody = """
                {
                "description": "",
                "note": null,
                "jeuId": 1
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/avis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidBody)
                        .accept(MediaType.APPLICATION_JSON)
                        .session(session))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldReturnInternalServerErrorWhenExceptionIsThrown() throws Exception {
        MockHttpSession session = new MockHttpSession();
        JoueurDTO joueurMock = new JoueurDTO();
        joueurMock.setId(42L);
        session.setAttribute("utilisateur", joueurMock);

        when(avisController.createAvis(Mockito.any(AvisDTO.class)))
                .thenThrow(new RuntimeException("Unexpected error"));

        String jsonBody = """
        {
            "description": "Ce jeu est incroyablement bien conçu et équilibré.",
            "note": 4.5,
            "jeuId": 1
        }
        """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/avis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
                        .accept(MediaType.APPLICATION_JSON)
                        .session(session))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string("Une erreur est survenue lors de la création de l'avis"))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    @WithMockUser(roles = "USER")
    void shouldReturnUnauthorizedWhenNoUserInSession() throws Exception {
        String jsonBody = """
        {
            "description": "Ce jeu est incroyablement bien conçu et équilibré.",
            "note": 4.5,
            "jeuId": 1
        }
        """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/avis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.content().string("Vous devez être connecté en tant que joueur pour poster un avis"))
                .andDo(MockMvcResultHandlers.print());
    }


}
