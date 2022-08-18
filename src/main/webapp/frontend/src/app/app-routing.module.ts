import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {IssuerComponent} from "./issuer/issuer.component";
import {EditComponent} from "./issuer/edit/edit.component";
import {DeleteComponent} from "./issuer/delete/delete.component";
import {CryptoConfigComponent} from "./cryptoconfig/crypto-config.component";
import {ProfileComponent} from './profiles/profile/profile.component';
import {ProfilesetComponent} from './profiles/profileset/profileset.component';
import {SubIssuerComponent} from "./subissuer/sub-issuer.component";
import {EditsubissuerComponent} from "./subissuer/edit/editsubissuer.component";
import {DeleteSubissuerComponent} from "./subissuer/delete/delete-subissuer.component";
import {ImageComponent} from "./image/image.component";
import {EditimageComponent} from "./image/edit/editimage.component";
import {DeleteImageComponent} from "./image/delete/delete-image.component";
import {RuleComponent} from './rule/rule.component';
import {RuleDeleteComponent} from "./rule/rule-delete/rule-delete.component";
import {RuleEditComponent} from "./rule/rule-edit/rule-edit.component";
import {RuleConditionComponent} from "./rulecondition/rule-condition.component";
import {ConditionDeleteComponent} from "./rulecondition/conditiondelete/condition-delete.component";
import {ConditionEditComponent} from "./rulecondition/conditionedit/condition-edit.component";
import {ProfileEditComponent} from './profiles/profile/profile-edit/profile-edit.component';
import {ProfileDeleteComponent} from './profiles/profile/profile-delete/profile-delete.component';
import {ProfilesetEditComponent} from './profiles/profileset/profileset-edit/profileset-edit.component';
import {ProfilesetDeleteComponent} from './profiles/profileset/profileset-delete/profileset-delete.component';
import {AuthentmeanComponent} from "./authentmean/authentmean/authentmean.component";
import {AuthentMeanEditComponent} from "./authentmean/edit/edit.component";
import {AuthentMeanDeleteComponent} from "./authentmean/delete/delete.component";
import { CryptoconfigDeleteComponent } from './cryptoconfig/cryptoconfig-delete/cryptoconfig-delete.component';
import { CryptoconfigEditComponent } from './cryptoconfig/cryptoconfig-edit/cryptoconfig-edit.component';

const routes: Routes = [
  {path: '', component: IssuerComponent},
  {path: 'issuer/:id/edit', component: EditComponent},
  {path: 'issuer/:id/delete', component: DeleteComponent},
  {path: '', component: SubIssuerComponent},
  {path: 'image', component: ImageComponent},
  {path: 'image/edit/:id', component: EditimageComponent},
  {path: 'image/delete/:id', component: DeleteImageComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'profile/:id/edit', component: ProfileEditComponent},
  {path: 'profile/:profile_id/delete', component: ProfileDeleteComponent},
  {path: 'profileset', component: ProfilesetComponent},
  {path: 'profileset/:id/edit', component: ProfilesetEditComponent},
  {path: 'profileset/:id/delete', component: ProfilesetDeleteComponent},
  {path: 'subissuer', component: SubIssuerComponent},
  {path: 'subissuer/:code/edit', component: EditsubissuerComponent},
  {path: 'subissuer/:code/:issuer_id/:crypto_id/delete', component: DeleteSubissuerComponent},
  {path: 'crypto/:id/delete', component: CryptoconfigDeleteComponent},
  {path: 'crypto/:id/edit', component: CryptoconfigEditComponent},
  {path: 'crypto', component: CryptoConfigComponent},
  {path: 'authent', component: AuthentmeanComponent},
  {path: 'authent/edit/:id', component: AuthentMeanEditComponent},
  {path: 'authent/delete/:id', component: AuthentMeanDeleteComponent},
  {path: 'rule', component: RuleComponent},
  {path: 'rule/delete/:id/:profile_id', component: RuleDeleteComponent},
  {path: 'rule/edit/:id', component: RuleEditComponent},
  {path: 'condition', component: RuleConditionComponent},
  {path: 'condition/delete/:id', component: ConditionDeleteComponent},
  {path: 'condition/edit/:id', component: ConditionEditComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}

export const routesList = [
  IssuerComponent, EditComponent, DeleteComponent,
  CryptoConfigComponent,
  AuthentmeanComponent, AuthentMeanEditComponent, AuthentMeanDeleteComponent,
  SubIssuerComponent, EditsubissuerComponent, DeleteSubissuerComponent,
  ImageComponent, EditimageComponent, DeleteImageComponent,
  RuleComponent, RuleDeleteComponent, RuleEditComponent,
  RuleConditionComponent, ConditionDeleteComponent, ConditionEditComponent,
  ProfileComponent, ProfileEditComponent, ProfileDeleteComponent,
  ProfilesetComponent, ProfilesetEditComponent, ProfilesetDeleteComponent
];


