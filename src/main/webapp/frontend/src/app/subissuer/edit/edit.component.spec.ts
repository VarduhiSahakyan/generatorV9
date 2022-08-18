import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditsubissuerComponent } from './editsubissuer.component';

describe('EditsubissuerComponent', () => {
  let component: EditsubissuerComponent;
  let fixture: ComponentFixture<EditsubissuerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditsubissuerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditsubissuerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
