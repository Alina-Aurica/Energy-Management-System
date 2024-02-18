import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FirstPageComponent} from "./pages/first-page/first-page.component";
import {LogInPageComponent} from "./pages/log-in-page/log-in-page.component";
import {RegisterComponent} from "./pages/register/register.component";
import {ClientPageComponent} from "./pages/client-page/client-page.component";
import {AdminPageComponent} from "./pages/admin-page/admin-page.component";
import {CrudDevicePageComponent} from "./pages/crud-device-page/crud-device-page.component";
import {CrudClientPageComponent} from "./pages/crud-client-page/crud-client-page.component";
import {GuardsService} from "./authorization/auth/guards.service";
import {AuthClientService} from "./authorization/authClient/auth-client.service";
import {AuthAdminService} from "./authorization/authAdmin/auth-admin.service";
import {ChartPageComponent} from "./pages/chart-page/chart-page.component";
import {ChatClientPageComponent} from "./pages/chat-client-page/chat-client-page.component";
import {ChatAdminPageComponent} from "./pages/chat-admin-page/chat-admin-page.component";

const routes: Routes = [
  {path: "firstPage", component:FirstPageComponent},
  {path: "logInPage", component:LogInPageComponent},
  {path: "registerPage", component:RegisterComponent},
  {path: "clientPage", component:ClientPageComponent, canActivate: [GuardsService, AuthClientService]},
  {path: "adminPage", component:AdminPageComponent, canActivate: [GuardsService, AuthAdminService]},
  {path: "crudDevicePage", component:CrudDevicePageComponent, canActivate: [GuardsService, AuthAdminService]},
  {path: "crudClientPage", component:CrudClientPageComponent, canActivate: [GuardsService, AuthAdminService]},
  {path: "chartPage", component:ChartPageComponent, canActivate: [GuardsService, AuthClientService]},
  {path: "chatClientPage", component:ChatClientPageComponent, canActivate: [GuardsService, AuthClientService]},
  {path: "chatAdminPage", component:ChatAdminPageComponent, canActivate: [GuardsService, AuthAdminService]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
