import {Component, OnDestroy, OnInit} from '@angular/core';
import {TeamStandingsService} from "./service/team-standings.service";
import {Observable} from "rxjs";
import {TeamStanding} from "./service/team-standing.interface";
import {map} from "rxjs/operators";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {

  ngOnInit(): void {

  }

  ngOnDestroy(): void {
  }

}
