import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
//import { CalendarModule, DatePickerModule, TimePickerModule, DateRangePickerModule, DateTimePickerModule } from '@syncfusion/ej2-angular-calendars';


import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { FirstPageComponent } from './pages/first-page/first-page.component';
import { LogInPageComponent } from './pages/log-in-page/log-in-page.component';
import { RegisterComponent } from './pages/register/register.component';
import { ClientPageComponent } from './pages/client-page/client-page.component';
import { AdminPageComponent } from './pages/admin-page/admin-page.component';
import { CrudDevicePageComponent } from './pages/crud-device-page/crud-device-page.component';
import { CrudClientPageComponent } from './pages/crud-client-page/crud-client-page.component';
import { WebSocketAPI } from "./services/websocket/WebSocketAPI";
import { ChartPageComponent } from './pages/chart-page/chart-page.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatInputModule} from "@angular/material/input";
import { ChatClientPageComponent } from './pages/chat-client-page/chat-client-page.component';
import { ChatAdminPageComponent } from './pages/chat-admin-page/chat-admin-page.component';
import {WebSocketChat} from "./services/websocket/WebSocketChat";
import {MatSnackBar} from "@angular/material/snack-bar";

@NgModule({
  declarations: [
    AppComponent,
    FirstPageComponent,
    LogInPageComponent,
    RegisterComponent,
    ClientPageComponent,
    AdminPageComponent,
    CrudDevicePageComponent,
    CrudClientPageComponent,
    ChartPageComponent,
    ChatClientPageComponent,
    ChatAdminPageComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    BrowserAnimationsModule,
    MatInputModule
  ],
  providers: [WebSocketAPI, WebSocketChat, MatSnackBar],
  bootstrap: [AppComponent]
})
export class AppModule { }
