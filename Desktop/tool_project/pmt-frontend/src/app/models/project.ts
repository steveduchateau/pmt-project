// src/app/models/project.model.ts
// src/app/models/project.model.ts
export interface Project {
  id: number; // ID requis
  name: string; // Nom du projet
  description: string;
  startDate: string; // Format YYYY-MM-DD
  creatorUserId: number | null; // Peut être null
  creatorEmail: string | null; // Peut être null
  adminId: number | null; // Peut être null
}