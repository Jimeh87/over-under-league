import {Component, Input, QueryList, ViewChildren} from '@angular/core';
import {HotColdIconComponent} from "../hot-cold-icon/hot-cold-icon.component";
import {NgClass, NgIf, TitleCasePipe} from "@angular/common";
import {SvgComponent} from "../svg/svg.component";
import {WholeNumberPipe} from "../whole-number.pipe";
import {UserStanding} from "../service/user-standing.interface";
import {ExpandCollapseRowDirective} from "../expand-collapse-row.directive";
import {animate, animateChild, query, style, transition, trigger} from "@angular/animations";

@Component({
    selector: 'app-standings',
    standalone: true,
    imports: [
        HotColdIconComponent,
        NgIf,
        SvgComponent,
        TitleCasePipe,
        WholeNumberPipe,
        ExpandCollapseRowDirective,
        NgClass
    ],
    templateUrl: './standings.component.html',
    styleUrl: './standings.component.scss',
    animations: [
        trigger('collapse', [
            transition(':enter', [
                style({height: '0px', minHeight: '0px', overflow: 'hidden', visibility: 'hidden'}),
                animate('150ms ease-out', style({height: '*', visibility: '*'}))
            ]),
            transition(':leave', [
                style({height: '*', minHeight: '*', visibility: '*', overflow: 'hidden'}),
                animate('150ms ease-out', style({height: '0px', minHeight: '0px', visibility: 'hidden'}))
            ])
        ]),
        trigger('propagateAnimation', [
            transition(':enter, :leave', [
                query('@*', animateChild())
            ])
        ])
    ]

})
export class StandingsComponent {

    @Input()
    standings!: UserStanding[];

    @ViewChildren(ExpandCollapseRowDirective)
    expandCollapseRows!: QueryList<ExpandCollapseRowDirective>;

    private collapseAll() {
        this.expandCollapseRows.forEach(row => row.collapse())
    }

    private expandAll() {
        this.expandCollapseRows.forEach(row => row.expand())
    }

    allExpanded(): boolean {
        if (!this.expandCollapseRows) {
            return false;
        }

        for (let i = 0; i < this.expandCollapseRows?.length; i++) {
            if (!this.expandCollapseRows.get(i)?.isExpanded()) {
                return false;
            }
        }

        return true;
    }

    expandCollapseAll() {
        if (this.allExpanded()) {
            this.collapseAll();
        } else {
            this.expandAll();
        }
    }
}
