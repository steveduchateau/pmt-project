import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-inscription',
  standalone: true,
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css'],
  imports: [CommonModule, ReactiveFormsModule]
})
export class InscriptionComponent implements OnInit {
  registerForm: FormGroup;
  errorMessage: string | null = null;
  successMessage: string | null = null; // Propriété pour le message de succès

  constructor(private fb: FormBuilder, private http: HttpClient) { 
    this.registerForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void { }

  onSubmit(): void {
    if (this.registerForm.valid) {
      this.http.post('http://localhost:8080/api/users/register', this.registerForm.value)
        .subscribe({
          next: (response) => {
            console.log('Inscription réussie', response);
            this.successMessage = 'Inscription réussie ! Vous allez être redirigé...'; // Définir le message de succès
            this.errorMessage = null; // Réinitialiser le message d'erreur en cas de succès

            // Recharge la page après un court délai
            setTimeout(() => {
              window.location.reload();
            }, 1500); // Délai de 1,5 seconde
          },
          error: (error) => {
            console.error('Erreur lors de l\'inscription', error);
            this.errorMessage = 'Erreur lors de l\'inscription. Veuillez réessayer.';
            this.successMessage = null; // Réinitialiser le message de succès en cas d'erreur
          }
        });
    }
  }
}
