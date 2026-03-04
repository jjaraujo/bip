import { CurrencyPipe, NgFor, NgIf } from '@angular/common';
import { Component, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { BeneficioApiService } from 'src/app/api/beneficio-api.service';
import { BeneficioEntity } from 'src/app/api/models';

@Component({
  standalone: true,
  imports: [RouterLink, NgIf, NgFor, CurrencyPipe],
  templateUrl: './beneficio-list.component.html',
  styleUrls: ['./beneficio-list.component.css']
})
export class BeneficioListComponent {
  items = signal<BeneficioEntity[]>([]);
  loading = signal(false);
  error = signal<string | null>(null);

  constructor(private api: BeneficioApiService) {
    this.reload();
  }

  reload() {
    this.loading.set(true);
    this.error.set(null);
    this.api.listAll().subscribe({
      next: (data) => this.items.set(data ?? []),
      error: (err) => this.error.set(this.prettyErr(err)),
      complete: () => this.loading.set(false),
    });
  }

  remove(b: BeneficioEntity) {
    if (!confirm(`Excluir o benefício #${b.id} (${b.nome})?`)) return;
    this.loading.set(true);
    this.error.set(null);
    this.api.delete(b.id).subscribe({
      next: () => this.reload(),
      error: (err) => {
        this.loading.set(false);
        this.error.set(this.prettyErr(err));
      },
    });
  }

  toMoney(valor: string): string {
    const n = Number(String(valor).replace(',', '.'));
    if (Number.isNaN(n)) return String(valor);
    return n.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
  }

  private prettyErr(err: any): string {
    const status = err?.status;
    const msg = err?.error?.message ?? err?.message ?? JSON.stringify(err);
    return `HTTP ${status ?? '??'}\n${msg}`;
  }
}
