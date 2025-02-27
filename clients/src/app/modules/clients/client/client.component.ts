import { Component, inject, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ClientsService } from '@core/services';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Client } from '@shared/models';

@Component({
  selector: 'app-client',
  imports: [ReactiveFormsModule],
  templateUrl: './client.component.html',
})
export class ClientComponent implements OnInit {
  private readonly _service = inject(ClientsService);
  readonly activeModal = inject(NgbActiveModal);

  id: number = 0;

  form = new FormGroup({
    username: new FormControl<string | null>(null, [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(10),
    ]),
    name: new FormControl<string | null>(null, [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(50),
    ]),
    email: new FormControl<string | null>(null, [
      Validators.required,
      Validators.email,
    ]),
    phone: new FormControl<string | null>(null, [
      Validators.required,
      Validators.pattern('\\d{10}'),
    ]),
    startDate: new FormControl<string | null>(null, [Validators.required]),
    endDate: new FormControl<string | null>(null, []),
  });

  ngOnInit(): void {
    if (this.id > 0) {
      this._service.getClientById(this.id).subscribe((client) => {
        this.form.patchValue({
          ...client,
          startDate: new Date(client.startDate).toISOString().split('T')[0],
          endDate: client.endDate
            ? new Date(client.endDate).toISOString().split('T')[0]
            : null,
        });
        console.log(this.form.value);
      });
    }
  }

  onSubmit() {
    this.form.markAllAsTouched();
    if (this.form.valid) {
      let client = {
        ...this.form.value,
        id: this.id,
        startDate: new Date(this.form.value.startDate as string),
        endDate: this.form.value.endDate
          ? new Date(this.form.value.endDate)
          : null,
      };
      if (this.id > 0) {
        client.id = this.id;
        this._service
          .updateClient(client as Client)
          .subscribe(() => this.activeModal.close(true));
      } else {
        this._service
          .createClient(client as Client)
          .subscribe(() => this.activeModal.close(true));
      }
    }
  }

  getError(control: AbstractControl) {
    return control.errors && Object.keys(control.errors)[0];
  }
}
