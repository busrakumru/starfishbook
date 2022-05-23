import { TestBed } from '@angular/core/testing';

import { ModalcontrollerService } from './modalcontroller.service';

describe('ModalcontrollerService', () => {
  let service: ModalcontrollerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ModalcontrollerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
