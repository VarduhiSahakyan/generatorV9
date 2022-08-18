import { TestBed } from '@angular/core/testing';

import { ProfilesetDeleteService } from './profileset-delete.service';

describe('ProfilesetDeleteService', () => {
  let service: ProfilesetDeleteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProfilesetDeleteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
