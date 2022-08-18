import { TestBed } from '@angular/core/testing';

import { ProfileDeleteServiceService } from './profile-delete-service.service';

describe('ProfileDeleteServiceService', () => {
  let service: ProfileDeleteServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProfileDeleteServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
