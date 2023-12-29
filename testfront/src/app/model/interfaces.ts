export interface Valores {
  geracao: number;
  compra: number;
  preco_medio: number;
}

export interface Pageable {
  pageNumber: number;
  pageSize: number;
}

export interface Page {
  content: Valores[];
  totalPages: number;
  totalElements: number;
  pageable: Pageable;
}  
