import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ImportListQuestionComponent } from './import-list-question.component';

describe('ImportListQuestionComponent', () => {
  let component: ImportListQuestionComponent;
  let fixture: ComponentFixture<ImportListQuestionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ImportListQuestionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImportListQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
