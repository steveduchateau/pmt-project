// src/app/app.config.ts
import { provideHttpClient } from '@angular/common/http';
import { provideRouter, Router } from '@angular/router';
import { provideAnimations } from '@angular/platform-browser/animations';
import { AppComponent } from './app.component';
import { withFetch } from '@angular/common/http'; // Importez withFetch

export const appConfig = {
  providers: [
    provideHttpClient(withFetch()), // Configurez HttpClient pour utiliser fetch
    provideRouter([
      // Définir vos routes ici
      { path: '', component: AppComponent }
    ]),
    provideAnimations()
  ]
};
