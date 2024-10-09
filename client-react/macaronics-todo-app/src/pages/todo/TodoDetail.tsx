import { getApiData } from '@/actions/axiosActions';
import TodoDetailComponent from '@/components/todo/TodoDetailComponent';
import { queryClient } from '@/utils/queryClient';
import { useQuery } from '@tanstack/react-query';
import React from 'react';

const TodoDetail: React.FC = () => {
  const todo = {
    id: 1,
    //title;
    description: '리액트 프로젝트를 마무리하고 리팩토링 작업을 진행하세요.',
    done: true, // 상태: 완료됨, 진행 중, 대기 중
    // priority: 'High', // 우선순위: 높음, 중간, 낮음
    targetDate: '2024-10-15', // 마감일
  };


//   id: number;
//   description: string;
//   done: boolean;
//   targetDate: Date;
//   userId ?:number;
//   username ?:string;
//   num ?:number;
//   link?:ApiResponseLink[];


  return (
    <>
        <TodoDetailComponent todo={todo} />
    </>
  );
};


interface LoaderParams {
    todoId: string;
}
 

export async function loader({params} : { params: LoaderParams }) {
    const {todoId} = params;
    ///api/users/{userId}/todos/{todoId}
    console.log(" params  :   ",todoId );
    return  queryClient.fetchQuery({
        queryKey: ["todo", params.todoId],
        queryFn: ({ signal }) => getApiData(`/api/users/1/todos/${todoId}`),
    });

    
}


export default TodoDetail;



