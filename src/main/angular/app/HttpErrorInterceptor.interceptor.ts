import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {Injectable} from "@angular/core";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ErrorSnackComponent} from "./error-snack/error-snack.component";

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {

    constructor(private snackBar: MatSnackBar) {
    }


    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request)
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    let errorMessage = '';
                    if (error.error instanceof ErrorEvent) {
                        // client-side error
                        errorMessage = `Error: ${error.error.message}`;
                    } else {
                        // server-side error
                        errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
                    }

                    this.snackBar.openFromComponent(ErrorSnackComponent, {duration: 30000, data: error, horizontalPosition: "center", verticalPosition: "top"});
                    return throwError(errorMessage);
                })
            )
    }
}