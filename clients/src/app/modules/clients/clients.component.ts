import { DatePipe } from '@angular/common';
import {
  Component,
  computed,
  inject,
  model,
  OnInit,
  signal,
} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ClientsService } from '@core/services';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Client } from '@shared/models';
import { ClientComponent } from './client/client.component';

@Component({
  selector: 'app-clients',
  imports: [DatePipe, FormsModule],
  templateUrl: './clients.component.html',
})
export class ClientsComponent implements OnInit {
  private readonly _service = inject(ClientsService);
  private readonly _modal = inject(NgbModal);

  clients = signal<Client[]>([]);

  searchQuery = model<string>('');
  isInvalidSearch = computed(() => {
    const value = this.searchQuery();
    return value && (value.length < 3 || value.length > 10);
  });

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients() {
    this._service.getClients(this.searchQuery()).subscribe((clients) => {
      this.clients.set(clients);
    });
  }

  onSearchChange($event: any) {
    this.searchQuery.set($event);
    !this.isInvalidSearch() && this.loadClients();
  }

  onCreateOrEdit(id: number) {
    const modal = this._modal.open(ClientComponent, {
      centered: true,
      backdrop: 'static',
      keyboard: false,
      scrollable: true,
      size: 'lg',
    });
    modal.componentInstance.id = id;
    modal.result.then((result) => {
      if (result) {
        this.loadClients();
      }
    });
  }
}
