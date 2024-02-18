import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrudClientPageComponent } from './crud-client-page.component';

describe('CrudClientPageComponent', () => {
  let component: CrudClientPageComponent;
  let fixture: ComponentFixture<CrudClientPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CrudClientPageComponent]
    });
    fixture = TestBed.createComponent(CrudClientPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
