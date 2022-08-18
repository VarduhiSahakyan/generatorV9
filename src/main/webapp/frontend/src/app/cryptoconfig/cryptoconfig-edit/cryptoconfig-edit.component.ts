import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { saveAs } from 'file-saver';
import { CryptoconfigEditService } from './cryptoconfig-edit.service';

@Component({
  selector: 'app-cryptoconfig-edit',
  templateUrl: './cryptoconfig-edit.component.html',
  styleUrls: ['./cryptoconfig-edit.component.css']
})
export class CryptoconfigEditComponent implements OnInit {

  id:any;
  filename:string = "";
  cryptoConfigUpdateForm: any;
  cryptoConfig: any; 

  constructor(
    private formBuilder: FormBuilder,
    private cryptoConfigEditService: CryptoconfigEditService,
    private route: ActivatedRoute
    ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get("id");
    this.initializeUpdateForm();
    this.getCryptoById(this.id);
  }

  private initializeUpdateForm() {
    this.cryptoConfigUpdateForm = this.formBuilder.group({
      protocolOne: ['', Validators.required],
      protocolTwo: ['', Validators.required],
      description: ['', Validators.required]
    });
  }

  getCryptoById(id:any){
     this.cryptoConfigEditService.getCryptoConfig(id).subscribe(response => {
              this.cryptoConfig = response;
     })
  }

  updateCryptoConfig() {
    let cryptoConfigData = this.cryptoConfigUpdateForm.value;
    this.cryptoConfigEditService.updateCryptoConfig(cryptoConfigData,this.id).subscribe(response => {
        this.filename = response;
    });
  }

  downloadSqlFile(){
    this.cryptoConfigEditService.downloadFile(this.filename).subscribe(file => saveAs(file,this.filename));
  }
  

}
