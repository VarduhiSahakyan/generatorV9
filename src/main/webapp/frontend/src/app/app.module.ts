import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {Ng2SearchPipeModule} from 'ng2-search-filter';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AppRoutingModule, routesList} from './app-routing.module';
import {AppComponent} from './app.component';
import {MainComponent} from './layouts/main/main.component';
import {NavbarComponent} from './layouts/navbar/navbar.component';
import {ImageComponent} from './image/image.component';
import {EditimageComponent} from './image/edit/editimage.component';
import {DeleteImageComponent} from './image/delete/delete-image.component';
import {RuleComponent} from './rule/rule.component';
import {RuleDeleteComponent} from './rule/rule-delete/rule-delete.component';
import {RuleEditComponent} from './rule/rule-edit/rule-edit.component';
import {RuleConditionComponent} from './rulecondition/rule-condition.component';
import {ConditionEditComponent} from './rulecondition/conditionedit/condition-edit.component';
import {ConditionDeleteComponent} from './rulecondition/conditiondelete/condition-delete.component';
import {CryptoConfigComponent } from "./cryptoconfig/crypto-config.component";
import {AuthentmeanComponent } from './authentmean/authentmean/authentmean.component';
import {AuthentMeanDeleteComponent} from './authentmean/delete/delete.component';
import {AuthentMeanEditComponent} from './authentmean/edit/edit.component';
import { CryptoconfigEditComponent } from './cryptoconfig/cryptoconfig-edit/cryptoconfig-edit.component';
import { CryptoconfigDeleteComponent } from './cryptoconfig/cryptoconfig-delete/cryptoconfig-delete.component';




@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    NavbarComponent,
    routesList,
    ImageComponent,
    EditimageComponent,
    DeleteImageComponent,
    RuleComponent,
    RuleDeleteComponent,
    RuleEditComponent,
    RuleConditionComponent,
    ConditionEditComponent,
    ConditionDeleteComponent,
    CryptoConfigComponent,
    AuthentmeanComponent,
    AuthentMeanEditComponent,
    AuthentMeanDeleteComponent,
    CryptoconfigEditComponent,
    CryptoconfigDeleteComponent
  ],

  imports: [
    AppRoutingModule,
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgbModule,
    Ng2SearchPipeModule,
    FormsModule,
  ],

  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule {
}
