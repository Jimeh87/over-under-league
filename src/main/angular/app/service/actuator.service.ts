import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ActuatorBuild} from "./actuator-build.interface";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ActuatorService {

  constructor(private httpClient: HttpClient) { }

  public getBuild(): Observable<ActuatorBuild> {
    return this.httpClient.get<any>('/actuator/info').pipe(map(response => response.build))
  }


}
