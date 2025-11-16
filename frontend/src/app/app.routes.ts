import { Routes } from '@angular/router';
import { ContasPage } from './pages/contas-page/contas-page';

export const routes: Routes = [
  { path: '', component: ContasPage },
  { path: '**', redirectTo: '' }
];
