import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RepartidorFormComponent } from './repartidor-form.component';

describe('RepartidorFormComponent', () => {
  let component: RepartidorFormComponent;
  let fixture: ComponentFixture<RepartidorFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RepartidorFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RepartidorFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
