import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChatAdminPageComponent } from './chat-admin-page.component';

describe('ChatAdminPageComponent', () => {
  let component: ChatAdminPageComponent;
  let fixture: ComponentFixture<ChatAdminPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ChatAdminPageComponent]
    });
    fixture = TestBed.createComponent(ChatAdminPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
