import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {TeamStanding} from "../service/team-standing.interface";
import {map} from "rxjs/operators";
import {TeamStandingsService} from "../service/team-standings.service";

@Component({
  selector: 'app-over-under',
  templateUrl: './over-under.component.html',
  styleUrls: ['./over-under.component.scss']
})
export class OverUnderComponent implements OnInit {

  $teamStandings: Observable<TeamStanding[]> = this.teamStandingsService.getStandings()
      .pipe(
          map(standings => standings.sort((s1, s2) => s2.pace - s1.pace))
      );

  constructor(private teamStandingsService: TeamStandingsService) {
  }

  ngOnInit() {
  }

}
