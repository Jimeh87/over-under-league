import {Component} from '@angular/core';
import {UserStandingsService} from "../service/user-standings.service";

@Component({
  selector: 'app-user-scores',
  templateUrl: './user-scores.component.html',
  styleUrls: ['./user-scores.component.scss']
})
export class UserScoresComponent {

  $userStandings = this.userStandingsService.getStandings();

  constructor(private userStandingsService: UserStandingsService) { }

}
