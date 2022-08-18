import { TestBed } from '@angular/core/testing';

import { CryptoconfigDeleteService } from './cryptoconfig-delete.service';

describe('CryptoconfigDeleteService', () => {
  let service: CryptoconfigDeleteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CryptoconfigDeleteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
