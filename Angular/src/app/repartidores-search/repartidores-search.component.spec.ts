import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RepartidoresSearchComponent } from './repartidores-search.component';

describe('RepartidoresSearchComponent', () => {
  let component: RepartidoresSearchComponent;
  let fixture: ComponentFixture<RepartidoresSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RepartidoresSearchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RepartidoresSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
