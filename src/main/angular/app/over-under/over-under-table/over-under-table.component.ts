import {Component, Input, OnInit} from '@angular/core';
import {TeamStanding} from "../../service/team-standing.interface";
import {Sort} from "@angular/material/sort";

@Component({
    selector: 'app-over-under-table',
    templateUrl: './over-under-table.component.html',
    styleUrls: ['./over-under-table.component.scss']
})
export class OverUnderTableComponent implements OnInit {

    @Input()
    teamStandings: TeamStanding[];

    columns = ['team', 'overunder', 'pace'];

    constructor() {
    }

    ngOnInit() {
    }


    sortData(sort: Sort) {
        const data = this.teamStandings.slice();
        if (!sort.active || sort.direction === '') {
            this.teamStandings = data;
            return;
        }

        this.teamStandings = data.sort((a, b) => {
            return this.compare(a[sort.active], b[sort.active], sort.direction === 'asc');
        });
    }

    compare(a: number | string, b: number | string, asc: boolean) {
        return (a < b ? -1 : 1) * (asc ? 1 : -1);
    }
}

