import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-connexion',
  standalone: true,
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.scss'],
  imports: [CommonModule, ReactiveFormsModule]
})
export class ConnexionComponent implements OnInit, OnDestroy {
  form(form: any) {
    throw new Error('Method not implemented.');
  }
  loginFailed(loginFailed: any) {
    throw new Error('Method not implemented.');
  }
  loginForm: FormGroup;
  errorMessage: string | null = null;
  successMessage: string | null = null;
  userEmailSubscription: Subscription | undefined; // Remplacez `private` par `protected`

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private authService: AuthService,
    private snackBar: MatSnackBar
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.userEmailSubscription = this.authService.getAuthenticatedUserEmail().subscribe(email => {
      if (email) {
        console.log('Utilisateur déjà connecté avec l\'email:', email);
        this.router.navigate(['/creation-projet']);
      }
    });
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      const { email, password } = this.loginForm.value;

      this.authService.login(email, password).subscribe({
        next: (response) => {
          if (response.message === 'Login successful') {
            this.authService.handleLogin(response);
            this.successMessage = 'Connexion réussie !';
            this.router.navigate(['/creation-projet']);
          } else {
            this.errorMessage = response.message || 'Identifiants incorrects.';
          }
        },
        error: (error) => {
          this.errorMessage = 'Une erreur est survenue lors de la connexion. Veuillez réessayer.';
          this.snackBar.open(this.errorMessage, 'Fermer', {
            duration: 3000,
            panelClass: ['snackbar-error'],
          });
          console.error('Erreur de connexion:', error);
        }
      });
    }
  }

  ngOnDestroy(): void {
    this.userEmailSubscription?.unsubscribe(); // Nettoyer l'abonnement à l'email
  }
}
