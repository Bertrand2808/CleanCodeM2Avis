package fr.esgi.avis.controller.Joueur.rest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import fr.esgi.avis.controller.Joueur.JoueurController;
import fr.esgi.avis.controller.Joueur.dto.JoueurDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class JoueurRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JoueurController joueurController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldCreateJoueurSuccessfully() throws Exception {
        // GIVEN
        JoueurDTO joueurDTO = createJoueurDTO();
        when(joueurController.createJoueur(any(JoueurDTO.class))).thenReturn(joueurDTO);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/joueurs")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "pseudo": "PlayerOne",
                            "motDePasse": "password123",
                            "email": "player@example.com",
                            "dateDeNaissance": "1995-06-15"
                        }
                        """)
                .accept(MediaType.APPLICATION_JSON);

        // WHEN & THEN
        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pseudo").value(joueurDTO.getPseudo()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(joueurDTO.getEmail()))
                .andDo(MockMvcResultHandlers.print());

        verify(joueurController, times(1)).createJoueur(any(JoueurDTO.class));
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldReturnJoueurByIdSuccessfully() throws Exception {
        // GIVEN
        Long joueurId = 1L;
        JoueurDTO joueurDTO = createJoueurDTO();
        when(joueurController.getJoueurById(joueurId)).thenReturn(Optional.of(joueurDTO));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/joueurs/id/1")
                .accept(MediaType.APPLICATION_JSON);

        // WHEN & THEN
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pseudo").value(joueurDTO.getPseudo()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(joueurDTO.getEmail()))
                .andDo(MockMvcResultHandlers.print());

        verify(joueurController, times(1)).getJoueurById(joueurId);
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldReturnNotFoundWhenJoueurDoesNotExist() throws Exception {
        // GIVEN
        Long joueurId = 99L;
        when(joueurController.getJoueurById(joueurId)).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/joueurs/id/99")
                .accept(MediaType.APPLICATION_JSON);

        // WHEN & THEN
        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

        verify(joueurController, times(1)).getJoueurById(joueurId);
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldAssignAvatarToJoueurSuccessfully() throws Exception {
        // GIVEN
        Long joueurId = 1L;
        Long avatarId = 2L;
        JoueurDTO joueurDTO = createJoueurDTO();
        when(joueurController.assignAvatarToJoueur(joueurId, avatarId)).thenReturn(Optional.of(joueurDTO));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/api/joueurs/1/set-avatar/2")
                .accept(MediaType.APPLICATION_JSON);

        // WHEN & THEN
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pseudo").value(joueurDTO.getPseudo()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(joueurDTO.getEmail()))
                .andDo(MockMvcResultHandlers.print());

        verify(joueurController, times(1)).assignAvatarToJoueur(joueurId, avatarId);
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldReturnNotFoundWhenAssigningAvatarToNonExistingJoueur() throws Exception {
        // GIVEN
        Long joueurId = 99L;
        Long avatarId = 2L;
        when(joueurController.assignAvatarToJoueur(joueurId, avatarId)).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/api/joueurs/99/set-avatar/2")
                .accept(MediaType.APPLICATION_JSON);

        // WHEN & THEN
        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

        verify(joueurController, times(1)).assignAvatarToJoueur(joueurId, avatarId);
    }

    private JoueurDTO createJoueurDTO() {
        JoueurDTO joueurDTO = new JoueurDTO();
        joueurDTO.setId(1L);
        joueurDTO.setPseudo("PlayerOne");
        joueurDTO.setMotDePasse("password123");
        joueurDTO.setEmail("player@example.com");
        joueurDTO.setDateDeNaissance(LocalDate.of(1995, 6, 15));
        return joueurDTO;
    }
}
