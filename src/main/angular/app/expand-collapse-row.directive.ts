import {
    Directive,
    ElementRef,
    EmbeddedViewRef,
    EventEmitter,
    HostListener,
    Input,
    Output,
    Renderer2,
    TemplateRef,
    ViewContainerRef
} from '@angular/core';

@Directive({
    selector: '[appExpandCollapseRow]',
    standalone: true
})
export class ExpandCollapseRowDirective {

    @Input('appExpandCollapseRow') template?: TemplateRef<any>;
    @Input('appExpandCollapseRowContext') context!: any;
    @Output('appExpandCollapseRowExpanded') expandedChange = new EventEmitter<boolean>();

    private expanded: boolean = false;
    private expansionRowViewRef?: EmbeddedViewRef<any>;

    constructor(private element: ElementRef,
                private renderer: Renderer2,
                private viewContainer: ViewContainerRef) {
    }

    @HostListener('click')
    onClick(): void {
        if (this.expansionRowViewRef) {
            this.collapse();
        } else {
            this.expand();
        }
    }

    public collapse() {
        if (!this.expansionRowViewRef) {
            return;
        }

        this.expanded = false;
        this.expandedChanged();

        const rowElement = this.element.nativeElement;
        requestAnimationFrame(() => {
            this.expansionRowViewRef?.rootNodes.forEach(node =>
                this.renderer.removeChild(rowElement.parent, node)
            );
            this.expansionRowViewRef = undefined;
        });
    }

    private expandedChanged() {
        this.expandedChange.emit(this.expanded);
    }

    public expand() {
        if (!this.template) {
            return;
        }
        if (this.expansionRowViewRef) {
            return;
        }

        this.expanded = true;
        this.expandedChanged();

        this.expansionRowViewRef = this.viewContainer.createEmbeddedView(this.template, this.context);
        const rowElement = this.element.nativeElement;

        requestAnimationFrame(() => {
            this.expansionRowViewRef?.rootNodes.forEach(node =>
                this.renderer.insertBefore(rowElement.parent, node, this.renderer.nextSibling(rowElement))
            );
        });
    }

    public isExpanded(): boolean {
        return this.expanded;
    }

}
