import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatTableModule} from "@angular/material/table";
import {HttpClientModule} from "@angular/common/http";
import {OverUnderComponent} from './over-under/over-under.component';
import {OverUnderTableComponent} from './over-under/over-under-table/over-under-table.component';
import {MatSortModule} from "@angular/material/sort";
import {UserScoresComponent} from './user-scores/user-scores.component';
import {UserScoresTableComponent} from './user-scores/user-scores-table/user-scores-table.component';
import {UserScoresTableDetailComponent} from './user-scores/user-scores-table/user-scores-table-detail/user-scores-table-detail.component';
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatTabsModule} from "@angular/material/tabs";
import {MatToolbarModule} from "@angular/material/toolbar";

@NgModule({
    declarations: [
        AppComponent,
        OverUnderComponent,
        OverUnderTableComponent,
        UserScoresComponent,
        UserScoresTableComponent,
        UserScoresTableDetailComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        BrowserAnimationsModule,
        MatTableModule,
        MatSortModule,
        MatButtonModule,
        MatIconModule,
        MatTabsModule,
        MatToolbarModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
