import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CryptoconfigDeleteComponent } from './cryptoconfig-delete.component';

describe('CryptoconfigDeleteComponent', () => {
  let component: CryptoconfigDeleteComponent;
  let fixture: ComponentFixture<CryptoconfigDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CryptoconfigDeleteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CryptoconfigDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
