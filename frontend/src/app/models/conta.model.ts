export interface ContaCreateRequest {
  name: string;
  originalAmount: number;
  dueDate: string; // 'yyyy-MM-dd'
  paymentDate: string;  // 'yyyy-MM-dd'
}

export interface ContaResponse {
  id?: number;
  name: string;
  originalAmount: number;
  adjustedAmount: number;
  lateDays: number;
  dueDate: string;
  paymentDate: string;
}
