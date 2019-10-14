import {Component, Input, OnInit} from '@angular/core';
import {TeamScore} from "../../../service/user-standing.interface";

@Component({
  selector: 'app-user-scores-table-detail',
  templateUrl: './user-scores-table-detail.component.html',
  styleUrls: ['./user-scores-table-detail.component.scss']
})
export class UserScoresTableDetailComponent implements OnInit {

  @Input()
  teamScores: TeamScore[];

  columns = ['team', 'winLose', 'wager', 'points'];

  ngOnInit() {
  }

}
