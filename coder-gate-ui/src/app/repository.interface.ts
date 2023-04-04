export interface Repository {
    timestamp: number;
    id: number;
    name: string;
  }
  
  export interface RepositoryResponse {
    repositories: Repository[];
  }