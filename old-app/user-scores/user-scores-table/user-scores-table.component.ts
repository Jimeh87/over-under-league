import {Component, Input} from '@angular/core';
import {UserStanding} from "../../service/user-standing.interface";
import {animate, state, style, transition, trigger} from "@angular/animations";

@Component({
  selector: 'app-user-scores-table',
  templateUrl: './user-scores-table.component.html',
  styleUrls: ['./user-scores-table.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ]
})
export class UserScoresTableComponent {

  @Input()
  userStandings: UserStanding[];

  columns = ['user', 'points', 'showDetailButton'];

  expandedRow: UserStanding;

}
