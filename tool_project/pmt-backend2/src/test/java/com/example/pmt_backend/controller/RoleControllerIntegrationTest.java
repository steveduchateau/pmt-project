package com.example.pmt_backend.controller;

import com.example.pmt_backend.model.Role;
import com.example.pmt_backend.Repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") // Indique à Spring d'utiliser le profil "test"
public class RoleControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    public void setUp() {
        // Nettoyez la base de données avant chaque test
        roleRepository.deleteAll();
    }

    @Test
    public void testGetAllRoles() throws Exception {
        // Créez les rôles pour le test
        Role adminRole = new Role();
        adminRole.setName("Admin");
        roleRepository.save(adminRole); // Enregistrez le rôle Admin

        Role memberRole = new Role();
        memberRole.setName("Member");
        roleRepository.save(memberRole); // Enregistrez le rôle Member

        Role observerRole = new Role();
        observerRole.setName("Observer");
        roleRepository.save(observerRole); // Enregistrez le rôle Observer

        // Vérifiez que l'endpoint retourne les rôles correctement
        mockMvc.perform(get("/auth/user_role")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Admin")) // Vérifiez que le rôle "Admin" est présent
                .andExpect(jsonPath("$[1].name").value("Member")) // Vérifiez que le rôle "Member" est présent
                .andExpect(jsonPath("$[2].name").value("Observer")); // Vérifiez que le rôle "Observer" est présent
    }

    @Test
    public void testCreateRole() throws Exception {
        // Créez un rôle sous forme de JSON
        String roleJson = "{\"name\": \"Member\"}";

        // Testez la création d'un rôle
        mockMvc.perform(post("/auth/user_role")
                .contentType(MediaType.APPLICATION_JSON)
                .content(roleJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Member")); // Vérifiez que le nom du rôle créé est "Member"
    }

    @Test
    public void testUpdateRole() throws Exception {
        // Créez un rôle initial
        Role role = new Role();
        role.setName("Observer");
        role = roleRepository.save(role); // Enregistrez le rôle dans la base de données

        // Préparez la mise à jour du rôle
        String updatedRoleJson = "{\"name\": \"Super Observer\"}";

        // Testez la mise à jour du rôle
        mockMvc.perform(put("/auth/user_role/" + role.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedRoleJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Super Observer")); // Vérifiez que le nom du rôle mis à jour est "Super Observer"
    }
}
