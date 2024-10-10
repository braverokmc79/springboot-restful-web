// src/components/todo/ListTodosComponent.tsx

import React, { useState } from 'react';
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import LoadingSpinner from '../common/LoadingComponent';
import { useNavigate } from 'react-router-dom';
import { ApiResponse, TodoDTO } from '@/dto/TodoDTO';
import DialogDeleteConfirmComponent from '../common/DialogDeleteConfirmComponent';
import { useQuery, useMutation } from '@tanstack/react-query';
import { getApiData, deleteApiData } from '@/actions/axiosActions';
import { queryClient } from '@/utils/queryClient';

const fetchTodos = async (): Promise<TodoDTO[]> => {
  const { apiData, error } = await getApiData("/api/users/1/todos");
  if (apiData) {
    const apiResponse = apiData as ApiResponse; // ApiResponse 타입에 맞게 조정
    if(apiResponse.data !== null && apiResponse.data.content !== null){
      return apiResponse.data.content;
    }
  }
  throw new Error(error?.message || 'Failed to fetch todos');
};

const deleteTodo = async (id: number): Promise<void> => {
  const { apiData, error } = await deleteApiData(`/api/users/1/todos/${id}`);
  if (apiData) {
    const apiResponse = apiData as ApiResponse; // ApiResponse 타입에 맞게 조정
    if(apiResponse.code !== 1){
      throw new Error(`삭제 실패: ${apiResponse.errorCode}`);
    }
  } else {
    throw new Error(error?.message || 'Failed to delete todo');
  }
};

const ListTodosComponent: React.FC = () => {
  const navigate = useNavigate();
  //const queryClient = useQueryClient();

  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [todoToDeleteId, setTodoToDeleteId] = useState<number | null>(null);

  // 할일 목록 가져오기
  const { data: todos, isLoading, isError, error } = useQuery<TodoDTO[], Error>({
    queryKey: ['todos'],queryFn: fetchTodos
  });


  // 할일 삭제 mutation
  const mutation = useMutation<void, Error, number>({
    mutationFn: deleteTodo,
    onSuccess: () => {
      // 삭제 후 'todos' 쿼리 무효화하여 데이터 다시 가져오기          
       // todos 쿼리 무효화 후, 최신 데이터 가져오기
      queryClient.invalidateQueries({ queryKey: ['todos'] });
    },
    onError: (err) => {
      console.error(err.message);
      // 추가적인 오류 처리 로직을 여기에 추가할 수 있습니다.
    }
  });

  const handleDeleteClick = (id: number) => {
    setTodoToDeleteId(id);
    setIsDialogOpen(true);
  };

  const confirmDelete = () => {
    if (todoToDeleteId !== null) {
      mutation.mutate(todoToDeleteId);
      setTodoToDeleteId(null);
      setIsDialogOpen(false);
    }
  };

  if (isLoading) return <LoadingSpinner />;
  if (isError) return <p className='text-3xl font-bold text-center mt-10'>Error occurred: {error.message}</p>;

  return (
    <div className="mt-20 flex md:justify-center">
      <div className="w-96 md:w-full md:max-w-6xl px-4">
        <Table className='w-full shadow-md rounded-lg'>
          {/* <TableCaption className="text-lg font-semibold text-gray-600">할일 목록</TableCaption> */}
          <TableHeader className="bg-gradient-to-r from-blue-500 to-blue-600 text-white">
            <TableRow>
              <TableHead className="w-[100px] text-left p-4 text-white">ID</TableHead>
              <TableHead className="text-center p-4 text-white w-5/12">내용</TableHead>
              <TableHead className="text-center p-4 text-white">완료 여부</TableHead>
              <TableHead className="text-center p-4 text-white">마감일</TableHead>
              <TableHead className="text-center p-4 text-white">처리</TableHead>
            </TableRow>
          </TableHeader>

          <TableBody className="bg-white">
            {todos && todos.map(todo => (
              <TableRow key={todo.id} className="border-b hover:bg-gray-100 transition text-center">
                <TableCell className="font-medium p-4 text-blue-600">{todo.id}</TableCell>
                <TableCell className="p-4 cursor-pointer hover:text-blue-600"
                  onClick={() => navigate(`/todo/${todo.id}`)}
                >
                  {todo.description}
                </TableCell>
                <TableCell className="p-4">
                  {todo.done ? (
                    <span className="text-green-500 font-bold">YES</span>
                  ) : (
                    <span className="text-red-500 font-bold">NO</span>
                  )}
                </TableCell>
                <TableCell className="p-4">{todo.targetDate}</TableCell>
                <TableCell className="p-4 space-y-2 md:space-y-0">
                  <button className='px-4 py-2 mx-2 bg-orange-500 rounded-sm text-white hover:bg-orange-800'>
                    수정
                  </button>
                  <button className='px-4 py-2 mx-2 bg-red-500 rounded-sm text-white hover:bg-red-800'
                    onClick={() => handleDeleteClick(todo.id)}
                  >
                    삭제
                  </button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </div>

      {/* 삭제 확인 다이얼로그 */}
      <DialogDeleteConfirmComponent  
        isDialogOpen={isDialogOpen}
        setIsDialogOpen={setIsDialogOpen}
        confirmDelete={confirmDelete} 
      />
    </div>
  );
};

export default ListTodosComponent;
