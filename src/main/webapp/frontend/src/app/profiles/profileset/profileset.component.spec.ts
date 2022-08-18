import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilesetComponent } from './profileset.component';

describe('ProfilesetComponent', () => {
  let component: ProfilesetComponent;
  let fixture: ComponentFixture<ProfilesetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfilesetComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfilesetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
