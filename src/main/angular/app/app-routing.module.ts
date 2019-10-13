import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {OverUnderComponent} from "./over-under/over-under.component";
import {AppComponent} from "./app.component";


const routes: Routes = [
  {
    path: '',
    component: AppComponent,
    pathMatch: 'full'
  },
  {
    path: 'over-under',
    component: OverUnderComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
