import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: 'clients', pathMatch: 'full' },
  {
    path: 'clients',
    loadChildren: () =>
      import('./modules/clients/clients.routes').then((m) => m.clientsRoutes),
  },
  { path: '**', redirectTo: 'clients' },
];
