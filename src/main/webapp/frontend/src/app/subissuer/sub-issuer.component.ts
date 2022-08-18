import {Component, OnInit} from '@angular/core';
import {SubIssuerService} from "./subIssuer.service";
import {FormBuilder, Validators} from "@angular/forms";
import {saveAs} from 'file-saver';
import {IssuerService} from "../issuer/issuer.service";
import {CryptoConfigService} from "../cryptoconfig/crypto-config.service";

@Component({
  selector: 'app-subissuer',
  templateUrl: './sub-issuer.component.html',
  styleUrls: ['./sub-issuer.component.css']
})
export class SubIssuerComponent implements OnInit {

  subIssuers: any;
  subIssuerForm: any;
  filename: string = "";
  issuers: any ;
  cryptoConfigs: any ;
  validMessage: string = "";

  constructor(private subIssuerService: SubIssuerService,
              private formBuilder: FormBuilder,
              private issuerService: IssuerService,
              private criptoService: CryptoConfigService
  ) { }

  ngOnInit(): void {
    this.initializeForm();
    this.getAllSubIssuer();
    this.index();
  }

  sendSubIssuerData() {
    let subIssuerData = this.subIssuerForm.value;
    if (this.subIssuerForm.valid) {
      this.validMessage = "Insert query submitted. You can insert again!";
      this.subIssuerService.sendData(subIssuerData).subscribe(
        response => {
          this.subIssuerForm.reset();
          this.filename = response;
          return true;
        },
        error => {
          return (error);
        }
      )
    } else {
      this.validMessage = "Please fill out the form before submitting!";
    }
  }

  initializeForm() {
    this.subIssuerForm = this.formBuilder.group({
      acsId: ['', Validators.required],
      authenticationTimeOut: [0, [Validators.required, Validators.pattern("^[0-9]*$")]],
      defaultLanguage: ['', Validators.required],
      code: ['', [Validators.required, Validators.pattern("^[0-9]*$")]],
      codeSvi: ['', Validators.required],
      currencyCode: ['', Validators.required],
      name: ['', Validators.required],
      label: ['', Validators.required],
      authentMeans: ['', Validators.required],
      personnalDataStorage: [0, Validators.required],
      resetBackupsIfSuccess: [0, Validators.required],
      resetChoicesIfSuccess: [0, Validators.required],
      manageBackupsCombinedAmounts: [0, Validators.required],
      manageChoicesCombinedAmounts: [0, Validators.required],
      hubMaintenanceModeEnabled: [0, Validators.required],
      issuer_id: ['', Validators.required],
      cryptoConfig_id: ['', Validators.required]
    });
  }

  getAllSubIssuer() {
    this.subIssuerService.getAllSubIssuer().subscribe(subIssuers => {
      this.subIssuers = subIssuers;
    });
  }

  downloadFile() {
    this.subIssuerService.downloadSqlFile(this.filename).subscribe(file => saveAs(file, this.filename));
  }

  index(){
    this.issuerService.getAllIssuer().subscribe(response => {this.issuers = response});
    this.criptoService.getCryptoConfigList().subscribe(response =>{this.cryptoConfigs = response});
  }

  setSubissuerProperties(){
    this.subIssuerService.getSubissuerProperties().subscribe(response =>{
      this.subIssuerForm.patchValue(response);
    })
  }
}

