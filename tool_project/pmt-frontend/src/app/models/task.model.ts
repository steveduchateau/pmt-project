import { Project } from "./project";

export interface Task {
  id: number;
  name: string;
  description: string;
  dueDate: string;
  priority: string;
  assignedBy: string;
  assignedTo: string;
  assignedUsers: string[];
  status: string;
  project: Project;
  createdBy: string | null;
  updatedBy: string | null;
  createdAt: string | null;
  updatedAt: string | null;
  history?: any[]; // Add the history property, adjust type as needed
}
