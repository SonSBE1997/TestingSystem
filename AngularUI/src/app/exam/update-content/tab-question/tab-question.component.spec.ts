import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TabQuestionComponent } from './tab-question.component';

describe('TabQuestionComponent', () => {
  let component: TabQuestionComponent;
  let fixture: ComponentFixture<TabQuestionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TabQuestionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TabQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
