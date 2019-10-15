import {Component, Inject} from '@angular/core';
import {HttpErrorResponse} from "@angular/common/http";
import {MatIconRegistry} from "@angular/material/icon";
import {DomSanitizer} from "@angular/platform-browser";
import {MAT_SNACK_BAR_DATA} from "@angular/material/snack-bar";

@Component({
  selector: 'app-error-snack',
  templateUrl: './error-snack.component.html',
  styleUrls: ['./error-snack.component.scss']
})
export class ErrorSnackComponent {

  error: HttpErrorResponse;
  errorCopied = false;

  constructor(@Inject(MAT_SNACK_BAR_DATA) public data: HttpErrorResponse,
              iconRegistry: MatIconRegistry,
              sanitizer: DomSanitizer) {
    this.error = data;

    iconRegistry.addSvgIcon(
        'file-copy',
        sanitizer.bypassSecurityTrustResourceUrl('assets/file_copy-24px.svg'));
  }

  copied() {
    this.errorCopied = true;
  }
}
