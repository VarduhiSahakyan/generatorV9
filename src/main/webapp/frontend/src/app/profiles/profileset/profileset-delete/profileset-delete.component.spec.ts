import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilesetDeleteComponent } from './profileset-delete.component';

describe('ProfilesetDeleteComponent', () => {
  let component: ProfilesetDeleteComponent;
  let fixture: ComponentFixture<ProfilesetDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfilesetDeleteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfilesetDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
