export interface Subissuer {

  acsId: string;
  authenticationTimeOut: number;
  defaultLanguage: string;
  code: string;
  codeSvi: string;
  currencyCode: string;
  name: string;
  label: string;
  authentMeans: string;
  personnalDataStorage: boolean;
  resetBackupsIfSuccess: boolean;
  resetChoicesIfSuccess: boolean;
  manageBackupsCombinedAmounts: boolean;
  manageChoicesCombinedAmounts: boolean;
  hubMaintenanceModeEnabled: boolean;
  issuer_id: number;
  cryptoConfig_id: number;

}

