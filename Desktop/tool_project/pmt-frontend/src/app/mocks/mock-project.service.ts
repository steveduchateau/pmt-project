import { of } from 'rxjs';
import { Project } from '../models/project';

export class MockProjectService {
  // Simule la récupération d'un projet par son ID
  getProject(id: number) {
    // Simulez une réponse de l'API pour le projet demandé
    return of({
      id: id,
      name: 'Project Name', // Assurez-vous que cette propriété est présente
      description: 'Project Description',
      startDate: '2024-10-29' // Format YYYY-MM-DD
    } as Project); // Utilisez le type Project pour la conversion
  }

  // Simule la récupération de tous les projets
  getAllProjects() {
    return of([
      {
        id: 1,
        name: 'Project 1',
        description: 'Description of Project 1',
        startDate: '2024-01-01'
      },
      {
        id: 2,
        name: 'Project 2',
        description: 'Description of Project 2',
        startDate: '2024-02-01'
      }
    ] as Project[]); // Retournez une liste de projets
  }

  // Vous pouvez ajouter d'autres méthodes moquées si nécessaire
}
