import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntregaPutComponent } from './entrega-put.component';

describe('EntregaPutComponent', () => {
  let component: EntregaPutComponent;
  let fixture: ComponentFixture<EntregaPutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EntregaPutComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EntregaPutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
