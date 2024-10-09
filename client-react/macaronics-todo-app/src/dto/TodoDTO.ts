

export interface ApiResponse{
    code: number;
    data: ApiResponseData |null;
    errorCode:string |null;
    message: string |null;
  }
  
  export interface ApiResponseData{
    content: TodoDTO[] | null;
    link?:ApiResponseLink[];
  }
  
  export interface ApiResponseLink{
    href: string;
    rel: string;
  }
  
  export interface TodoDTO {
    id: number;
    description: string;
    done: boolean;
    targetDate: string | null;;
    userId ?:number;
    username ?:string;
    num ?:number;
    link?:ApiResponseLink[];
  }
  