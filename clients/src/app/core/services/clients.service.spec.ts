import { TestBed } from '@angular/core/testing';

import { HttpClient } from '@angular/common/http';
import { Client } from '@shared/models';
import { of } from 'rxjs';
import { ClientsService } from './clients.service';

describe('ClientsService', () => {
  let service: ClientsService;
  let http: HttpClient;

  const dummyClients: Client[] = [
    {
      id: 1,
      name: 'John Doe',
      username: '',
      email: '',
      phone: '',
      startDate: new Date(),
      endDate: null,
    },
    {
      id: 2,
      name: 'Jane Doe',
      username: '',
      email: '',
      phone: '',
      startDate: new Date(),
      endDate: null,
    },
  ];

  beforeEach(() => {
    http = {
      get: jasmine.createSpy('get').and.returnValue(of(dummyClients)),
      post: jasmine.createSpy('post').and.returnValue(of(dummyClients[0])),
      put: jasmine.createSpy('put').and.returnValue(of(dummyClients[1])),
      delete: jasmine.createSpy('delete').and.returnValue(of(true)),
    } as unknown as jasmine.SpyObj<HttpClient>;
    service = TestBed.configureTestingModule({
      providers: [
        ClientsService,
        {
          provide: HttpClient,
          useValue: http,
        },
      ],
    }).inject(ClientsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should retrieve clients', () => {
    service.getClients(null).subscribe((clients) => {
      expect(clients.length).toBe(2);
      expect(clients).toEqual(dummyClients);
    });
  });

  it('should retrieve a client by ID', () => {
    service.getClientById(1).subscribe((client) => {
      expect(client).toBeTruthy();
    });
  });

  it('should create a new client', () => {
    const newClient: Client = {
      id: 3,
      name: 'Sam Smith',
      username: '',
      email: '',
      phone: '',
      startDate: new Date(),
      endDate: null,
    };

    service.createClient(newClient).subscribe((client) => {
      expect(client).toEqual(dummyClients[0]);
    });
  });

  it('should update a client', () => {
    const updatedClient: Client = {
      id: 1,
      name: 'John Doe Updated',
      username: '',
      email: '',
      phone: '',
      startDate: new Date(),
      endDate: null,
    };

    service.updateClient(updatedClient).subscribe((client) => {
      expect(client).toEqual(dummyClients[1]);
    });
  });

  it('should delete a client', () => {
    service.deleteClient(1).subscribe((response) => {
      expect(response).toBeTrue();
    });
  });
});
