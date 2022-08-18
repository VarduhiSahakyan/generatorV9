import { TestBed } from '@angular/core/testing';

import { ProfilesetEditService } from './profileset-edit.service';

describe('ProfilesetEditService', () => {
  let service: ProfilesetEditService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProfilesetEditService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
