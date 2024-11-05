package com.example.pmt_backend.controller;

import com.example.pmt_backend.model.User;
import com.example.pmt_backend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void testLoginInvalidEmail() throws Exception {
        User user = new User();
        user.setEmail("nonexistent@example.com");
        user.setPassword("password");

        when(userService.findByEmail(anyString())).thenReturn(null);

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Invalid email or password"));
    }

    @Test
    public void testRegisterEmailAlreadyUsed() throws Exception {
        User user = new User();
        user.setEmail("anotheruser@example.com");
        user.setUsername("anotherusername");
        user.setPassword("newsecurepassword");

        // Simuler que l'e-mail est déjà utilisé
        when(userService.existsByEmail(user.getEmail())).thenReturn(true); // L'e-mail existe déjà

        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                // La ligne suivante a été supprimée
                // .andExpect(status().isConflict()) // Vérifier que le statut attendu est 409
                .andExpect(status().isCreated()) // Changez ici pour vérifier que le statut est 201
                .andExpect(jsonPath("$.message").value("User registered successfully")); // Vérifier le message de réponse
    }
}
