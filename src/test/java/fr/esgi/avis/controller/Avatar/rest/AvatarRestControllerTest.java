package fr.esgi.avis.controller.Avatar.rest;

import com.github.javafaker.Faker;
import fr.esgi.avis.controller.Avatar.AvatarController;
import fr.esgi.avis.controller.Avatar.dto.AvatarDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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

import java.util.Locale;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class AvatarRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AvatarController avatarController;

    private Faker faker;
    private AvatarDTO avatarDTO;

    @BeforeEach
    void setUp() {
        faker = new Faker(Locale.FRENCH);
        avatarDTO = new AvatarDTO();
        avatarDTO.setId(1L);
        avatarDTO.setNom(faker.funnyName().name()); // Génère un nom aléatoire pour l'avatar
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldCreateAvatarSuccessfully() throws Exception {
        // GIVEN
        when(avatarController.createAvatar(Mockito.any(AvatarDTO.class))).thenReturn(avatarDTO);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/avatars")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nom\":\"" + avatarDTO.getNom() + "\"}")
                .accept(MediaType.APPLICATION_JSON);

        // WHEN & THEN
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value(avatarDTO.getNom()))
                .andDo(MockMvcResultHandlers.print());

        verify(avatarController, times(1)).createAvatar(Mockito.any(AvatarDTO.class));
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldGetAvatarByIdSuccessfully() throws Exception {
        // GIVEN
        when(avatarController.getAvatarById(1L)).thenReturn(Optional.of(avatarDTO));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/avatars/1")
                .accept(MediaType.APPLICATION_JSON);

        // WHEN & THEN
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value(avatarDTO.getNom()))
                .andDo(MockMvcResultHandlers.print());

        verify(avatarController, times(1)).getAvatarById(1L);
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldReturnNotFoundWhenAvatarDoesNotExist() throws Exception {
        // GIVEN
        when(avatarController.getAvatarById(2L)).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/avatars/2")
                .accept(MediaType.APPLICATION_JSON);

        // WHEN & THEN
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

        verify(avatarController, times(1)).getAvatarById(2L);
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldDeleteAvatarSuccessfully() throws Exception {
        // GIVEN
        doNothing().when(avatarController).deleteAvatarById(1L);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/avatars/1")
                .accept(MediaType.APPLICATION_JSON);

        // WHEN & THEN
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());

        verify(avatarController, times(1)).deleteAvatarById(1L);
    }
}
