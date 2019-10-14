import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {OverUnderComponent} from "./over-under/over-under.component";
import {AppComponent} from "./app.component";
import {UserScoresComponent} from "./user-scores/user-scores.component";


const routes: Routes = [
  {
    path: 'league-standings',
    component: UserScoresComponent
  },
  {
    path: 'nba-standings',
    component: OverUnderComponent
  },
  {
    path: '**',
    redirectTo: 'league-standings'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
