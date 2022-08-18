import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CryptoConfigComponent } from './crypto-config.component';

describe('CryptoConfigComponent', () => {
  let component: CryptoConfigComponent;
  let fixture: ComponentFixture<CryptoConfigComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CryptoConfigComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CryptoConfigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
