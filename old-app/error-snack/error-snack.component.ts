import {Component, Inject} from '@angular/core';
import {HttpErrorResponse} from "@angular/common/http";
import {MAT_SNACK_BAR_DATA} from "@angular/material/snack-bar";

@Component({
  selector: 'app-error-snack',
  templateUrl: './error-snack.component.html',
  styleUrls: ['./error-snack.component.scss']
})
export class ErrorSnackComponent {

  error: HttpErrorResponse;
  errorCopied = false;

  constructor(@Inject(MAT_SNACK_BAR_DATA) public data: HttpErrorResponse) {
    this.error = data;
  }

  copied() {
    this.errorCopied = true;
  }
}
