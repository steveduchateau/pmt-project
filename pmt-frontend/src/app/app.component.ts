import { Component } from '@angular/core';
import { InscriptionComponent } from './inscription/inscription.component';

@Component({
  selector: 'app-root',
  template: `<app-inscription></app-inscription>`,
  standalone: true,
  imports: [InscriptionComponent],
})
export class AppComponent {
  // Logique de votre composant principal ici
}
