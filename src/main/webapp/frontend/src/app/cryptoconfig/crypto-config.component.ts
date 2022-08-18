import {Component, Input, OnInit} from '@angular/core';
import {CryptoConfigService} from "./crypto-config.service";
import {NgbModal, NgbModalConfig} from "@ng-bootstrap/ng-bootstrap";
import {FormBuilder, Validators} from "@angular/forms";
import {saveAs} from "file-saver";

@Component({
  selector: 'app-crypto-config',
  templateUrl: './crypto-config.component.html',
  styleUrls: ['./crypto-config.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class CryptoConfigComponent implements OnInit {

  cryptoConfigs: any;
  cryptoConfigAddForm: any;
  filename:string = "";
  validMessage: string = "";

  constructor(private cryptoConfigService: CryptoConfigService, private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.getCryptoConfigList();
    this.initializeAddForm();
  }

  sendCryptoConfigData() {
    let cryptoConfigData = this.cryptoConfigAddForm.value;
    if (this.cryptoConfigAddForm.valid) {
      this.validMessage = "Insert query submitted. You can insert again!";
      this.cryptoConfigService.addCryptoConfig(cryptoConfigData).subscribe(
        response => {
          this.cryptoConfigAddForm.reset();
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

  private initializeAddForm() {
    this.cryptoConfigAddForm = this.formBuilder.group({
      protocolOne: ['', Validators.required],
      protocolTwo: ['', Validators.required],
      description: ['', Validators.required]
    });
  }


  getCryptoConfigList() {
    this.cryptoConfigService.getCryptoConfigList().subscribe(cryptoConfigs => {
      this.cryptoConfigs = cryptoConfigs;
    });
  }



  downloadFile() {
    this.cryptoConfigService.downloadFile(this.filename).subscribe(file => saveAs(file, this.filename));
  }
}
