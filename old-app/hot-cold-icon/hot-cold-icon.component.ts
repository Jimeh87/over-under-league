import {Component, Input, OnInit} from '@angular/core';
import {MatIconRegistry} from "@angular/material/icon";
import {DomSanitizer} from "@angular/platform-browser";
import {TeamTemperature} from "../service/user-standing.interface";

@Component({
  selector: 'app-hot-cold-icon',
  templateUrl: './hot-cold-icon.component.html',
  styleUrls: ['./hot-cold-icon.component.scss']
})
export class HotColdIconComponent {

  @Input()
  temp: TeamTemperature;

}
