import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {DomSanitizer, SafeHtml} from "@angular/platform-browser";
import {Observable, of, tap} from "rxjs";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class SvgService {

  svgCache: Map<string, SafeHtml> = new Map<string, SafeHtml>();

  constructor(private httpClient: HttpClient,
              private sanitizer: DomSanitizer) {
  }

  get(name: string): Observable<SafeHtml> {
    if (this.svgCache.has(name)) {
      return of(<SafeHtml>this.svgCache.get(name));
    }

    return this.httpClient.get(`assets/${name}.svg`, {responseType: 'text'})
        .pipe(
            map(svg => this.sanitizer.bypassSecurityTrustHtml(svg)),
            tap(safeSvg => this.svgCache.set(name, safeSvg))
        );
  }

}
