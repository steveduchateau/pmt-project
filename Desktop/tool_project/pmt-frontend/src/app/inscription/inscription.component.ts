import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inscription',
  standalone: true,
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.scss'],
  imports: [CommonModule, ReactiveFormsModule]
})
export class InscriptionComponent implements OnInit {
  registerForm: FormGroup;
  errorMessage: string | null = null;
  successMessage: string | null = null;

  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) { 
    this.registerForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void { }

  onSubmit(): void {
    if (this.registerForm.valid) {
      this.http.post('http://localhost:8080/auth/register', this.registerForm.value)
        .subscribe({
          next: (response: any) => {
            console.log('Inscription réussie', response);
            this.successMessage = response.message; // Accéder au message de la réponse JSON
            this.errorMessage = null;

            // Redirige vers la page de connexion après un court délai
            setTimeout(() => {
              this.router.navigate(['/connexion']); // Remplacez la recharge de page par la navigation
            }, 1500);
          },
          error: (error) => {
            console.error('Erreur lors de l\'inscription', error);
            this.errorMessage = error.error.message || 'Erreur lors de l\'inscription. Veuillez réessayer.';
            this.successMessage = null;
          }
        });
    }
  }
}
