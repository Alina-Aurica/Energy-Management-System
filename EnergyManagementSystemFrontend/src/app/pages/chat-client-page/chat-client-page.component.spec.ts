import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChatClientPageComponent } from './chat-client-page.component';

describe('ChatClientPageComponent', () => {
  let component: ChatClientPageComponent;
  let fixture: ComponentFixture<ChatClientPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ChatClientPageComponent]
    });
    fixture = TestBed.createComponent(ChatClientPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
