import { Task } from "../models/task.model";

const mockTasks: Task[] = [
    {
      id: 1,
      name: 'Task 1',
      description: 'Description 1',
      dueDate: '2024-12-31',
      priority: 'High',
      assignedBy: 'User A',
      assignedTo: 'User B',
      assignedUsers: [],
      status: 'Non démarrée',
      project: {
        id: 1, name: 'Project 1', description: '', startDate: '',
        creatorUserId: null,
        creatorEmail: null,
        adminId: null
      },
      createdBy: null,
      updatedBy: null,
      createdAt: null,
      updatedAt: null
    },
    {
      id: 2,
      name: 'Task 2',
      description: 'Description 2',
      dueDate: '2024-12-31',
      priority: 'Medium',
      assignedBy: 'User A',
      assignedTo: 'User C',
      assignedUsers: [],
      status: 'En cours',
      project: {
        id: 1, name: 'Project 1', description: '', startDate: '',
        creatorUserId: null,
        creatorEmail: null,
        adminId: null
      },
      createdBy: null,
      updatedBy: null,
      createdAt: null,
      updatedAt: null
    },
    {
      id: 3,
      name: 'Task 3',
      description: 'Description 3',
      dueDate: '2024-12-31',
      priority: 'Low',
      assignedBy: 'User A',
      assignedTo: 'User D',
      assignedUsers: [],
      status: 'Terminée',
      project: {
        id: 2, name: 'Project 2', description: '', startDate: '',
        creatorUserId: null,
        creatorEmail: null,
        adminId: null
      },
      createdBy: null,
      updatedBy: null,
      createdAt: null,
      updatedAt: null
    }
  ];
  