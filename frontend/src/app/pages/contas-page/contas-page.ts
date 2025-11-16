import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators, FormsModule } from '@angular/forms';
import { ContaService } from '../../services/conta.service';
import { ContaCreateRequest, ContaResponse } from '../../models/conta.model';

@Component({
  selector: 'app-contas-page',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './contas-page.html',
  styleUrl: './contas-page.scss'
})
export class ContasPage implements OnInit {
  filtro: string = '';
  paginaAtual: number = 1;
  itensPorPagina: number = 10;
  contasFiltradas: ContaResponse[] = [];
  contasPaginadas: ContaResponse[] = [];

  form!: FormGroup;
  contas: ContaResponse[] = [];
  carregando: boolean = false;
  erro?: string;
  sucesso?: string;

  get totalPaginas(): number {
    return Math.max(1, Math.ceil(this.contasFiltradas.length / this.itensPorPagina));
  }

  constructor(
    private fb: FormBuilder,
    private contaService: ContaService
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      name: ['', [Validators.required]],
      originalAmount: [null, [Validators.required, Validators.min(0.01)]],
      dueDate: ['', [Validators.required]],
      paymentDate: ['', [Validators.required]]
    });

    this.carregarContas();
    setTimeout(() => {
      this.adicionarMascaraData('dueDate');
      this.adicionarMascaraData('paymentDate');
    }, 0);
  }

  carregarContas(): void {
    this.carregando = true;
    this.erro = undefined;
    this.contaService.listar().subscribe({
      next: (contas: ContaResponse[]) => {
        this.contas = contas;
        this.filtrarContas();
        this.carregando = false;
      },
      error: (err: any) => {
        console.error(err);
        this.erro = 'Erro ao carregar contas.';
        this.carregando = false;
        setTimeout(() => {
          this.erro = undefined;
        }, 4000);
      }
    });
  }

  adicionarMascaraData(campo: string) {
    const input = document.getElementById(campo) as HTMLInputElement;
    if (!input) return;
    input.addEventListener('input', (e: any) => {
      let v = e.target.value.replace(/\D/g, '').slice(0, 8);
      if (v.length >= 5) v = v.replace(/(\d{2})(\d{2})(\d{0,4})/, '$1/$2/$3');
      else if (v.length >= 3) v = v.replace(/(\d{2})(\d{0,2})/, '$1/$2');
      e.target.value = v;
    });
  }

  filtrarContas() {
    const filtro = this.filtro.trim().toLowerCase();
    this.contasFiltradas = this.contas.filter(c =>
      (!filtro ||
        (c.name && c.name.toLowerCase().includes(filtro)) ||
        (c.id && c.id.toString().includes(filtro))
      )
    );
    this.paginaAtual = 1;
    this.paginarContas();
  }

  paginarContas() {
    const start = (this.paginaAtual - 1) * this.itensPorPagina;
    const end = start + this.itensPorPagina;
    this.contasPaginadas = this.contasFiltradas.slice(start, end);
  }

  mudarPagina(delta: number) {
    const totalPaginas = Math.ceil(this.contasFiltradas.length / this.itensPorPagina);
    this.paginaAtual = Math.max(1, Math.min(this.paginaAtual + delta, totalPaginas));
    this.paginarContas();
  }

  onSubmit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.carregando = true;
    this.erro = undefined;
    this.sucesso = undefined;

    // Função para converter dd/MM/yyyy para yyyy-MM-dd
    function toIsoDate(dateStr: string): string {
      const [dd, mm, yyyy] = dateStr.split('/');
      return `${yyyy}-${mm.padStart(2, '0')}-${dd.padStart(2, '0')}`;
    }

    const formValue = this.form.value;
    const payload: ContaCreateRequest = {
      ...formValue,
      dueDate: toIsoDate(formValue.dueDate),
      paymentDate: toIsoDate(formValue.paymentDate)
    };

    this.contaService.criar(payload).subscribe({
      next: () => {
        this.sucesso = 'Conta cadastrada com sucesso!';
        this.form.reset();
        this.carregando = false;
        this.carregarContas();
        setTimeout(() => {
          this.adicionarMascaraData('dueDate');
          this.adicionarMascaraData('paymentDate');
        }, 0);
        setTimeout(() => {
          this.sucesso = undefined;
        }, 4000);
      },
      error: (err: any) => {
        console.error(err);
        this.erro = 'Erro ao cadastrar conta.';
        this.carregando = false;
        setTimeout(() => {
          this.erro = undefined;
        }, 4000);
      }
    });
  }

  hasError(field: string, error: string): boolean {
    const control = this.form.get(field);
    return !!control && control.touched && control.hasError(error);
  }
}
