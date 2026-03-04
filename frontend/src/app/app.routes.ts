import { Routes } from '@angular/router';
import { BeneficioEditComponent } from './pages/beneficio-edit/beneficio-edit.component';
import { BeneficioListComponent } from './pages/beneficio-list/beneficio-list.component';
import { TransferComponent } from './pages/transfer/transfer.component';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'beneficios' },
  { path: 'beneficios', component: BeneficioListComponent },
  { path: 'beneficios/novo', component: BeneficioEditComponent },
  { path: 'beneficios/:id/editar', component: BeneficioEditComponent },
  { path: 'transfer', component: TransferComponent },
  { path: '**', redirectTo: 'beneficios' },
];
