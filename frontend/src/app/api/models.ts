export interface BeneficioEntity {
  id: number;
  nome: string;
  descricao?: string | null;
  valor: number;
  ativo: boolean;
  version?: number | null;
}

export interface BeneficioDto {
  nome: string;
  descricao?: string | null;
  valor: number; 
  ativo: boolean;
}

export interface TransferRequest {
  fromId: number;
  toId: number;
  valor: string;
}
