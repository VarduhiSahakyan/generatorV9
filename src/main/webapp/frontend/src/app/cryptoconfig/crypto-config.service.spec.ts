import { TestBed } from '@angular/core/testing';

import { CryptoConfigService } from './crypto-config.service';

describe('CryptoConfigService', () => {
  let service: CryptoConfigService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CryptoConfigService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
