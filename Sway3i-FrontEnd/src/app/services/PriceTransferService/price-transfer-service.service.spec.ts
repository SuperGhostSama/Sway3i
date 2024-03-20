import { TestBed } from '@angular/core/testing';

import { PriceTransferServiceService } from './price-transfer-service.service';

describe('PriceTransferServiceService', () => {
  let service: PriceTransferServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PriceTransferServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
