import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCommonComponent } from './update-common.component';

describe('UpdateCommonComponent', () => {
  let component: UpdateCommonComponent;
  let fixture: ComponentFixture<UpdateCommonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateCommonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateCommonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
