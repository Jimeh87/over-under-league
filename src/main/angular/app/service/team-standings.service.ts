import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {TeamStanding} from "./team-standing.interface";

@Injectable({
  providedIn: 'root'
})
export class TeamStandingsService {

  constructor(private httpClient: HttpClient) { }

  getStandings(): Observable<TeamStanding[]> {
    return this.httpClient.get<TeamStanding[]>("/api/over-under-teams");
  }
}
