import {Component, Input, OnInit} from '@angular/core';
import {UserStanding} from "../../service/user-standing.interface";
import {Sort} from "@angular/material/sort";
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
export class UserScoresTableComponent implements OnInit {

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


  ngOnInit() {
  }


  sortData(sort: Sort) {
    const data = this.userStandings.slice();
    if (!sort.active || sort.direction === '') {
      this.userStandings = data;
      return;
    }

    this.userStandings = data.sort((a, b) => {
      return this.compare(a[sort.active], b[sort.active], sort.direction === 'asc');
    });
  }

  compare(a: number | string, b: number | string, asc: boolean) {
    return (a < b ? -1 : 1) * (asc ? 1 : -1);
  }
}
