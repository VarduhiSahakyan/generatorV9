import { TestBed } from '@angular/core/testing';

import { ProfileEditServiceService } from './profile-edit-service.service';

describe('ProfileEditServiceService', () => {
  let service: ProfileEditServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProfileEditServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
