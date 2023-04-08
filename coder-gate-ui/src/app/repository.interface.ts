export interface Repository {
    timestamp: number;
    id: number;
    name: string;
    health: string;
  }
  
  export interface RepositoryResponse {
    repositories: Repository[];
  }