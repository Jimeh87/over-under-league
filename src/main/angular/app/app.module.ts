import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatTableModule} from "@angular/material/table";
import {HttpClientModule} from "@angular/common/http";
import { OverUnderComponent } from './over-under/over-under.component';
import { OverUnderTableComponent } from './over-under/over-under-table/over-under-table.component';
import {MatSortModule} from "@angular/material/sort";

@NgModule({
    declarations: [
        AppComponent,
        OverUnderComponent,
        OverUnderTableComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        BrowserAnimationsModule,
        MatTableModule,
        MatSortModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
