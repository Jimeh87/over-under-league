import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {OverUnderComponent} from "./over-under/over-under.component";


const routes: Routes = [
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
