import { TestBed } from '@angular/core/testing';

import { ModalControllerService } from './modalcontroller.service';

describe('ModalcontrollerService', () => {
  let service: ModalControllerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ModalControllerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
