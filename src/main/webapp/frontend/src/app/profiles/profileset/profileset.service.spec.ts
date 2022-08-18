import { TestBed } from '@angular/core/testing';

import { ProfilesetService } from './profileset.service';

describe('ProfilesetService', () => {
  let service: ProfilesetService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProfilesetService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
