import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrudDevicePageComponent } from './crud-device-page.component';

describe('CrudDevicePageComponent', () => {
  let component: CrudDevicePageComponent;
  let fixture: ComponentFixture<CrudDevicePageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CrudDevicePageComponent]
    });
    fixture = TestBed.createComponent(CrudDevicePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
