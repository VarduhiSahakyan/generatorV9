import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilesetEditComponent } from './profileset-edit.component';

describe('ProfilesetEditComponent', () => {
  let component: ProfilesetEditComponent;
  let fixture: ComponentFixture<ProfilesetEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfilesetEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfilesetEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
