import { NgIf } from '@angular/common';
import { Component, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { BeneficioApiService } from 'src/app/api/beneficio-api.service';

@Component({
  standalone: true,
  imports: [RouterLink, ReactiveFormsModule, NgIf],
  templateUrl: './transfer.component.html',
  styleUrls: ['./transfer.component.css']
})
export class TransferComponent {
  sending = signal(false);
  error = signal<string | null>(null);
  ok = signal(false);

  form = this.fb.group({
    fromId: ['', [Validators.required]],
    toId: ['', [Validators.required]],
    valor: ['', [Validators.required]],
  });

  constructor(private fb: FormBuilder, private api: BeneficioApiService) {}

  submit() {
    if (this.form.invalid) return;
    this.sending.set(true);
    this.error.set(null);
    this.ok.set(false);

    const req = {
      fromId: Number(this.form.value.fromId),
      toId: Number(this.form.value.toId),
      valor: String(this.form.value.valor),
    };

    this.api.transfer(req).subscribe({
      next: () => {
        this.sending.set(false);
        this.ok.set(true);
      },
      error: (err) => {
        this.sending.set(false);
        this.error.set(this.prettyErr(err));
      },
    });
  }

  private prettyErr(err: any): string {
    const status = err?.status;
    const msg = err?.error?.message ?? err?.message ?? JSON.stringify(err);
    return `HTTP ${status ?? '??'}\n${msg}`;
  }
}
