import {Component} from '@angular/core';
import {MatIconRegistry} from "@angular/material/icon";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app2-root',
  templateUrl: './app2.component.html',
  styleUrls: ['./app2.component.scss']
})
export class App2Component {

  constructor(iconRegistry: MatIconRegistry, sanitizer: DomSanitizer) {
    iconRegistry.addSvgIcon('sports-basketball', sanitizer.bypassSecurityTrustResourceUrl('assets/sports_basketball-24px.svg'));
    iconRegistry.addSvgIcon('arrow-down', sanitizer.bypassSecurityTrustResourceUrl('assets/keyboard_arrow_down-24px.svg'));
    iconRegistry.addSvgIcon('arrow-up', sanitizer.bypassSecurityTrustResourceUrl('assets/keyboard_arrow_up-24px.svg'));
    iconRegistry.addSvgIcon('github', sanitizer.bypassSecurityTrustResourceUrl('assets/github.svg'));
  }

}
