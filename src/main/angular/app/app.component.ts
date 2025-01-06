import {Component, OnInit} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {UserStandingsService} from "./service/user-standings.service";
import {UserStanding} from "./service/user-standing.interface";
import {HeaderComponent} from "./header/header.component";
import {StandingsComponent} from "./standings/standings.component";
import {SvgComponent} from "./svg/svg.component";
import {FooterComponent} from "./footer/footer.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent, StandingsComponent, SvgComponent, FooterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {

  loading: boolean = true;
  standings: UserStanding[] = [];

  constructor(private userStandingsService: UserStandingsService) {
  }

  ngOnInit(): void {
    this.loading = true;
    this.userStandingsService.getStandings()
        .subscribe(standings => {
          this.standings = standings;
          this.loading = false;
        });
  }
}
