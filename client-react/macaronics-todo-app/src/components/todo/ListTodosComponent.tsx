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
import { useNavigate } from 'react-router-dom';
import { ApiResponse, TodoDTO } from '@/dto/TodoDTO';

const ListTodosComponent: React.FC = () => {
  const [todos, setTodos] = useState<TodoDTO[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<unknown>(null);
  const navigator=useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);

      const { apiData, error } = await getApiData("/api/users/1/todos");

      if (apiData) {        
        const apiResponse = apiData as ApiResponse;
        setTodos(apiResponse?.data?.content);
      }
      setError(error);
      setLoading(false);
    };

    fetchData();
  }, []);



  if (loading) return <LoadingSpinner />;
  if (error) return <p className='text-3xl font-bold text-center mt-10'>Error occurred: {(error as Error).message}</p>;

  return (
    <div className="mt-20 flex md:justify-center">
      <div className="w-96 md:w-full md:max-w-6xl px-4">
        <Table className='w-full shadow-md rounded-lg'>
          {/* <TableCaption className="text-lg font-semibold text-gray-600">할일 목록</TableCaption> */}
          <TableHeader className="bg-gradient-to-r from-blue-500 to-blue-600 text-white">
            <TableRow>
              <TableHead className="w-[100px] text-left p-4 text-white">ID</TableHead>
              <TableHead className="text-center p-4 text-white w-5/12 ">내용</TableHead>
              <TableHead className="text-center p-4 text-white">완료 여부</TableHead>
              <TableHead className="text-center p-4 text-white">마감일</TableHead>
              <TableHead className="text-center p-4 text-white">처리</TableHead>
            </TableRow>
          </TableHeader>

          <TableBody className="bg-white">
            {todos && todos.map((todo) => (
              <TableRow key={todo.id} className="border-b hover:bg-gray-100 transition text-center"
              
              >
                <TableCell className="font-medium p-4 text-blue-600">{todo.id}</TableCell>
                <TableCell className="p-4 cursor-pointer hover:text-blue-600"
                  onClick={()=>navigator(`/todo/${todo.id}`)}
                
                >{todo.description}</TableCell>
                <TableCell className="p-4">{todo.done ? (
                  <span className="text-green-500 font-bold">YES</span>
                ) : (
                  <span className="text-red-500 font-bold">NO</span>
                )}</TableCell>
                <TableCell className="p-4">{todo.targetDate}</TableCell>


                <TableCell className="p-4 space-y-2 md:space-y-0">
                    <button className='px-4 py-2 mx-2 bg-orange-500  rounded-sm text-white hover:bg-orange-800 
                     
                    '>수정</button>

                    <button className='px-4 py-2 mx-2 bg-red-500  rounded-sm text-white hover:bg-red-800 '>삭제</button>

                </TableCell>
              </TableRow>
            ))}

          </TableBody>
        </Table>
      </div>
    </div>
  );
};

export default ListTodosComponent;
