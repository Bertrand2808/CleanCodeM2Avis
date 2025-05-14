package fr.esgi.avis.controller.Avis.rest;

import com.github.javafaker.Faker;
import fr.esgi.avis.controller.Avis.AvisController;
import fr.esgi.avis.controller.Avis.dto.AvisDTO;
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
        when(avisController.createAvis(Mockito.any(AvisDTO.class))).thenReturn(avisDTO);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/avis")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"description\":\"" + avisDTO.getDescription() + "\", \"note\":4.5}")
                .accept(MediaType.APPLICATION_JSON);

        // WHEN & THEN
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
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
}
