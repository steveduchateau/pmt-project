package com.example.pmt_backend.service;

import com.example.pmt_backend.model.User;
import com.example.pmt_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private String currentUserEmail; // Champ pour stocker l'email de l'utilisateur connecté

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public void createUser(User user) {
        userRepository.save(user); // Sauvegarde simple sans hachage
    }

    public void setCurrentUserEmail(String email) {
        this.currentUserEmail = email;
    }

    public String getCurrentUserEmail() {
        return currentUserEmail;
    }

    // Méthode pour vérifier le mot de passe
    public boolean checkPassword(User user, String rawPassword) {
        // Implémentez ici la logique de vérification de mot de passe
        return user.getPassword().equals(rawPassword); // Remplacez ceci par votre logique de sécurité
    }
}
