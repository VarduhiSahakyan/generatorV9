import { TestBed } from '@angular/core/testing';

import { CryptoconfigEditService } from './cryptoconfig-edit.service';

describe('CryptoconfigEditService', () => {
  let service: CryptoconfigEditService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CryptoconfigEditService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
