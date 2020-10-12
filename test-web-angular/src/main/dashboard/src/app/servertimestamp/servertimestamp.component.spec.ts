import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServertimestampComponent } from './servertimestamp.component';

describe('ServertimestampComponent', () => {
  let component: ServertimestampComponent;
  let fixture: ComponentFixture<ServertimestampComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServertimestampComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServertimestampComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
