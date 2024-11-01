import { Component, OnInit } from '@angular/core';
import {ActuatorService} from "../service/actuator.service";
import {MatIconRegistry} from "@angular/material/icon";
import {DomSanitizer} from "@angular/platform-browser";
import {LastUpdatedService} from "../service/last-updated.service";

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {

  build$ = this.actuatorService.getBuild();
  lastUpdate$ = this.lastUpdatedService.get();

  constructor(private actuatorService: ActuatorService,
              private lastUpdatedService: LastUpdatedService) {
  }

  ngOnInit() {
  }

}
