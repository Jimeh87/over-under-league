import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserStanding} from "./user-standing.interface";

@Injectable({
  providedIn: 'root'
})
export class UserStandingsService {

  constructor(private httpClient: HttpClient) { }

  getStandings(): Observable<UserStanding[]> {
    return this.httpClient.get<UserStanding[]>("/api/user-scores");
  }

}
