package com.example.pmt_backend.controller;

import com.example.pmt_backend.model.Role;
import com.example.pmt_backend.Repository.RoleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/user_role")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    // Récupérer tous les rôles
    @GetMapping
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Créer un nouveau rôle
    @PostMapping
    public Role createRole(@Valid @RequestBody Role role) {
        return roleRepository.save(role);
    }

    // Modifier un rôle existant
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable(value = "id") Long roleId, @Valid @RequestBody Role roleDetails) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with id " + roleId));

        role.setName(roleDetails.getName());
        final Role updatedRole = roleRepository.save(role);
        return ResponseEntity.ok(updatedRole);
    }
}
