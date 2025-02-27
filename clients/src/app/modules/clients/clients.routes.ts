import { Routes } from '@angular/router';

const clientsRoutes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./clients.component').then((m) => m.ClientsComponent),
  },
];

export { clientsRoutes };
