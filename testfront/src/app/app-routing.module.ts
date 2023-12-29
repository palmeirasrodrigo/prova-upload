import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaRegiaoComponent } from './views/lista-regiao/lista-regiao.component';

const routes: Routes = [
  {
    path: "",
    redirectTo: 'lista-regiao',
    pathMatch: 'full'
  },
  {
    path: 'lista-regiao',
    component: ListaRegiaoComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
