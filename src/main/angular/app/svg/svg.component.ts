import {Component, Input, OnChanges} from '@angular/core';
import {SafeHtml} from '@angular/platform-browser';
import {SvgService} from "../svg.service";

@Component({
    selector: 'app-svg',
    standalone: true,
    imports: [],
    template: `<div [innerHTML]="svgIcon"></div>`,
})
export class SvgComponent implements OnChanges {
    @Input() name?: string;

    svgIcon?: SafeHtml;

    constructor(private svgService: SvgService) {
    }

    public ngOnChanges(): void {
        if (!this.name) {
            console.log(`Svg name is missing.`);
            this.svgIcon = '';
            return;
        }

        this.svgService.get(this.name)
            .subscribe(svgIcon => this.svgIcon = svgIcon);
    }

}
