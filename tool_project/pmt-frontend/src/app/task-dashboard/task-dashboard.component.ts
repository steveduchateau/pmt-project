import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TaskService } from '../services/task.service';
import { ProjectService } from '../services/project.service';
import { FormsModule } from '@angular/forms';
import { Project } from '../models/project';
import { Task } from '../models/task.model';

interface TaskHistoryEntry {
  oldDescription: any;
  newDescription: any;
  oldStatus: any;
  newStatus: any;
  oldTask: any;
  newTask: any;
  task: any;
  modifiedAt: Date;
  modifiedBy: string;
  action: string;
}

@Component({
  selector: 'task-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './task-dashboard.component.html',
  styleUrls: ['./task-dashboard.component.scss']
})
export class TaskDashboardComponent implements OnInit {
  pendingTasks: Task[] = [];
  inProgressTasks: Task[] = [];
  completedTasks: Task[] = [];
  selectedTask: Task | null = null;
  taskHistory: TaskHistoryEntry[] = [];
  isLoading: boolean = false;

  projects: Project[] = [];
  selectedProjectName: string = 'All';
    tasks: any;

  constructor(private taskService: TaskService, private projectService: ProjectService) {}

  ngOnInit(): void {
    this.loadTasks();
    this.loadProjects();
  }

  loadTasks(): void {
    this.isLoading = true;
    this.taskService.getTasks().subscribe({
      next: (tasks: Task[]) => {
        this.filterTasks(tasks);
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des tâches', err);
      },
      complete: () => {
        this.isLoading = false;
      }
    });
  }

  loadProjects(): void {
    this.projectService.getProjects().subscribe({
      next: (projects: Project[]) => {
        this.projects = projects;
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des projets', err);
      }
    });
  }

  filterTasks(tasks: Task[]): void {
    if (this.selectedProjectName === 'All') {
      this.pendingTasks = tasks.filter(task => task.status === 'Non démarrée');
      this.inProgressTasks = tasks.filter(task => task.status === 'En cours');
      this.completedTasks = tasks.filter(task => task.status === 'Terminée');
    } else {
      this.pendingTasks = tasks.filter(task => task.project && task.project.name === this.selectedProjectName && task.status === 'Non démarrée');
      this.inProgressTasks = tasks.filter(task => task.project && task.project.name === this.selectedProjectName && task.status === 'En cours');
      this.completedTasks = tasks.filter(task => task.project && task.project.name === this.selectedProjectName && task.status === 'Terminée');
    }
  }

  onProjectFilterChange(): void {
    this.loadTasks(); // Recharge les tâches après avoir changé de projet
  }

  closeTaskHistory(): void {
    this.selectedTask = null;
    this.taskHistory = [];
  }

  viewTaskHistory(task: Task): void {
    this.selectedTask = task;
    this.isLoading = true;
    this.taskService.getTaskHistory(task.id).subscribe({
      next: (history: TaskHistoryEntry[]) => {
        this.taskHistory = history;
      },
      error: (err) => {
        console.error('Erreur lors de la récupération de l\'historique des tâches', err);
      },
      complete: () => {
        this.isLoading = false;
      }
    });
  }
}
