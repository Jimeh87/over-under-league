import {Component, OnDestroy, OnInit} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {UserStandingsService} from "./service/user-standings.service";
import {UserStanding} from "./service/user-standing.interface";
import {HeaderComponent} from "./header/header.component";
import {StandingsComponent} from "./standings/standings.component";
import {SvgComponent} from "./svg/svg.component";
import {FooterComponent} from "./footer/footer.component";
import {LOADING_MESSAGES} from "./loading-messages.constants";

@Component({
    selector: 'app-root',
    standalone: true,
    imports: [RouterOutlet, HeaderComponent, StandingsComponent, SvgComponent, FooterComponent],
    templateUrl: './app.component.html',
    styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit, OnDestroy {
    error: boolean = false;
    loading: boolean = true;
    standings: UserStanding[] = [];
    loadingMessages: string[] = LOADING_MESSAGES;
    currentMessage: string = '';
    private messageInterval?: any;

    constructor(private userStandingsService: UserStandingsService) {
    }

    ngOnInit(): void {
        this.loading = true;
        this.currentMessage = this.getRandomMessage();
        this.rotateMessages();
        
        this.userStandingsService.getStandings()
            .subscribe({
                next: standings => {
                    this.standings = standings;
                    this.loading = false;
                    this.clearMessageInterval();
                },
                error: err => {
                    this.error = true;
                    this.clearMessageInterval();
                }
            });
    }

    ngOnDestroy(): void {
        this.clearMessageInterval();
    }

    private rotateMessages(): void {
        this.messageInterval = setInterval(() => {
            if (this.loading) {
                this.currentMessage = this.getRandomMessage();
            } else {
                this.clearMessageInterval();
            }
        }, 3000);
    }

    private getRandomMessage(): string {
        const randomIndex = Math.floor(Math.random() * this.loadingMessages.length);
        return this.loadingMessages[randomIndex];
    }

    private clearMessageInterval(): void {
        if (this.messageInterval) {
            clearInterval(this.messageInterval);
            this.messageInterval = undefined;
        }
    }
}
