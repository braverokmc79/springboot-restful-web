import React, { useEffect, useState } from 'react';
import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { getApiData } from '@/actions/axiosActions';
import LoadingSpinner from '../common/LoadingComponent';



export interface ApiResponse{
  code: number;
  data: ApiResponseData |null;
  errorCode:string |null;
  message: string |null;
}

export interface ApiResponseData{
  content: object[] | null;
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
  targetDate: Date;
  userId ?:number;
  username ?:string;
  num ?:number;
  link?:ApiResponseLink[];
}

const ListTodosComponent: React.FC = () => {
  const [todos, setTodos] = useState<TodoDTO[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<unknown>(null);


  useEffect(()=>{

    const fetchData = async () => {
      setLoading(true);
     
      const {apiData, error} =await getApiData("/api/users/1/todos");

      console.log("apiData =========== ", apiData);


      if(apiData){        
        const apiResponse = apiData as ApiResponse;  
        setTodos(apiResponse?.data?.content);       
      }     
      setError(error);
      setLoading(false);
    };

    fetchData();

  }, []);


 
  if (loading) return  <LoadingSpinner  />;
  if (error) return <p className='text-3xl font-bold text-center mt-10'>Error occurred: {(error as Error).message}</p>;

  return (
    <div className="mt-20 flex md:justify-center">
      <div className="w-96  md:w-full md:max-w-6xl px-4">
        <Table className='w-full'>
          <TableCaption>할일 목록</TableCaption>
          <TableHeader className="bg-slate-200">
            <TableRow>
              <TableHead className="w-[100px]">ID</TableHead>
              <TableHead className="text-center">내용</TableHead>
              <TableHead className="text-center">완료 여부</TableHead>
              <TableHead className="text-center">마감일</TableHead>
            </TableRow>
          </TableHeader>

          <TableBody>
            {todos && todos.map((todo) => (
              <TableRow key={todo.id} className="border-b text-center">
                <TableCell className="font-medium">{todo.id}</TableCell>
                <TableCell>{todo.description}</TableCell>
                <TableCell>{todo.done===true?"YES":"NO"}</TableCell>
                <TableCell>{todo.targetDate}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </div>
    </div>
  );
}

export default ListTodosComponent;
