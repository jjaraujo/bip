import { NgIf } from '@angular/common';
import { Component, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { BeneficioApiService } from '../../api/beneficio-api.service';
import { BeneficioDto } from '../../api/models';

@Component({
  standalone: true,
  imports: [RouterLink, ReactiveFormsModule, NgIf],
  templateUrl: './beneficio-edit.component.html',
  styleUrls: ['./beneficio-edit.component.css']
})
export class BeneficioEditComponent {
  id = signal<number | null>(null);
  isEdit = signal(false);
  saving = signal(false);
  error = signal<string | null>(null);

  form = this.fb.group({
    nome: ['', [Validators.required, Validators.minLength(2)]],
    descricao: [''],
    valor: ['', [Validators.required]],
    ativo: [true],
  });

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private api: BeneficioApiService,
  ) {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      const id = Number(idParam);
      this.id.set(id);
      this.isEdit.set(true);
      this.load(id);
    }
  }

  private load(id: number) {
    this.api.findById(id).subscribe({
      next: (b) => {
        this.form.patchValue({
          nome: b.nome,
          descricao: b.descricao ?? '',
          valor: String(b.valor),
          ativo: !!b.ativo,
        });
      },
      error: (err) => this.error.set(this.prettyErr(err)),
    });
  }

  save() {
    if (this.form.invalid) return;
    this.saving.set(true);
    this.error.set(null);

    const dto: BeneficioDto = {
      nome: this.form.value.nome!,
      descricao: this.form.value.descricao ?? '',
      valor: Number(this.form.value.valor ?? 0),
      ativo: !!this.form.value.ativo,
    };

    const id = this.id();
    const call = this.isEdit() && id != null
      ? this.api.update(id, dto)
      : this.api.create(dto);

    call.subscribe({
      next: () => this.router.navigateByUrl('/beneficios'),
      error: (err) => {
        this.saving.set(false);
        this.error.set(this.prettyErr(err));
      },
    });
  }

  cancel() {
    this.router.navigateByUrl('/beneficios');
  }

  private prettyErr(err: any): string {
    const status = err?.status;
    const msg = err?.error?.message ?? err?.message ?? JSON.stringify(err);
    return `HTTP ${status ?? '??'}\n${msg}`;
  }
}
