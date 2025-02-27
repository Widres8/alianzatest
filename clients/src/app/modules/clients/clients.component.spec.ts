import { TestBed } from '@angular/core/testing';

import { DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ClientsService } from '@core/services';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ClientsComponent } from './clients.component';

describe('ClientsComponent', () => {
  let component: ClientsComponent;
  let service: ClientsService;

  beforeEach(async () => {
    service = {} as unknown as jasmine.SpyObj<ClientsService>;
    component = TestBed.configureTestingModule({
      providers: [
        ClientsComponent,
        NgbModal,
        DatePipe,
        FormsModule,
        {
          provide: ClientsService,
          useValue: service,
        },
      ],
    }).inject(ClientsComponent);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
