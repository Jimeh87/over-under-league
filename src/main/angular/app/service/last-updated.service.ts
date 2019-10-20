import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class LastUpdatedService {

  constructor(private httpClient: HttpClient) { }

  public get(): Observable<string> {
    return this.httpClient.get<any>('/api/publish-date').pipe(map(response => response.lastUpdated))
  }

}
