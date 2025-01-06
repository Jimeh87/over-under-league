import {Component, OnInit} from '@angular/core';
import {ActuatorService} from "../service/actuator.service";
import {Observable} from "rxjs";
import {ActuatorBuild} from "../service/actuator-build.interface";
import {AsyncPipe, NgIf} from "@angular/common";
import {SvgComponent} from "../svg/svg.component";

@Component({
    selector: 'app-footer',
    templateUrl: './footer.component.html',
    styleUrls: ['./footer.component.scss'],
    imports: [
        AsyncPipe,
        SvgComponent,
        NgIf
    ],
    standalone: true
})
export class FooterComponent implements OnInit {

    build$!: Observable<ActuatorBuild>;

    constructor(private actuatorService: ActuatorService) {
    }

    ngOnInit() {
        this.build$ = this.actuatorService.getBuild();
    }

}
