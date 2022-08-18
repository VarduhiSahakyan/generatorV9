import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CryptoconfigEditComponent } from './cryptoconfig-edit.component';

describe('CryptoconfigEditComponent', () => {
  let component: CryptoconfigEditComponent;
  let fixture: ComponentFixture<CryptoconfigEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CryptoconfigEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CryptoconfigEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
