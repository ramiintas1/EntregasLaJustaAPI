import { TestBed } from '@angular/core/testing';

import { EstadorepartidorService } from './estadorepartidor.service';

describe('EstadorepartidorService', () => {
  let service: EstadorepartidorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EstadorepartidorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
