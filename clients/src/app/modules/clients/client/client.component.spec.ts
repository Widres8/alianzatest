import { TestBed } from '@angular/core/testing';

import { provideHttpClient } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ClientComponent } from './client.component';

describe('ClientComponent', () => {
  let component: ClientComponent;

  beforeEach(async () => {
    component = TestBed.configureTestingModule({
      imports: [ClientComponent],
      providers: [ClientComponent, NgbActiveModal, provideHttpClient()],
    }).inject(ClientComponent);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
