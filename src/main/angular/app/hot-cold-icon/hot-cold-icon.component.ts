import {Component, Input} from '@angular/core';
import {TeamTemperature} from "../service/user-standing.interface";
import {SvgComponent} from "../svg/svg.component";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-hot-cold-icon',
  templateUrl: './hot-cold-icon.component.html',
  standalone: true,
  imports: [
    SvgComponent,
    NgIf
  ],
  styleUrls: ['./hot-cold-icon.component.scss']
})
export class HotColdIconComponent {

  @Input()
  temp?: TeamTemperature;

}
