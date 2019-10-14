import {Component, Input} from '@angular/core';
import {UserStanding} from "../../service/user-standing.interface";
import {animate, state, style, transition, trigger} from "@angular/animations";
import {MatIconRegistry} from "@angular/material/icon";
import {DomSanitizer} from "@angular/platform-browser";

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

  columns = ['user', 'score', 'showDetailButton'];

  expandedRow: UserStanding;

  constructor(iconRegistry: MatIconRegistry, sanitizer: DomSanitizer) {
    iconRegistry.addSvgIcon(
        'arrow-down',
        sanitizer.bypassSecurityTrustResourceUrl('assets/keyboard_arrow_down-24px.svg'));
    iconRegistry.addSvgIcon(
        'arrow-up',
        sanitizer.bypassSecurityTrustResourceUrl('assets/keyboard_arrow_up-24px.svg'));
  }

}
