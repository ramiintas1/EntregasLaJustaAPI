import { TestBed } from '@angular/core/testing';

import { TransicionService } from './transicion.service';

describe('TransicionService', () => {
  let service: TransicionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TransicionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
