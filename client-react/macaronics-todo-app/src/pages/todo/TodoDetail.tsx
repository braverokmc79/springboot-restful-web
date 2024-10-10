import { getApiData } from "@/actions/axiosActions";
import LoadingSpinner from "@/components/common/LoadingComponent";
import TodoDetailComponent from "@/components/todo/TodoDetailComponent";
import { TodoDTO } from "@/dto/TodoDTO";
import { queryClient } from "@/utils/queryClient";
import { useQuery } from "@tanstack/react-query";
import React from "react";
import { useParams } from "react-router-dom";

const TodoDetail: React.FC = () => {
  const { todoId } = useParams<{ todoId: string }>();

  //useQuery로 데이터 가져오기
  const {data: todo, isLoading,error,} = useQuery({    
    queryKey: ["todo", todoId],
    queryFn: async () => {
        return await getApiData(`/api/users/1/todos/${todoId}`).then(response => response.apiData);      
    },
  });


  if (isLoading) return <LoadingSpinner />;
  if (error) return <p>에러가 발생했습니다: {(error as Error).message}</p>;
  

  return (
    <>
      {todo ? (
        <TodoDetailComponent todo={todo as TodoDTO} />
      ) : (
        <p>할 일을 찾을 수 없습니다.</p>
      )}
    </>
  );
};





interface LoaderParams {
  todoId: string;
}


// loader 함수
export async function loader({ params }: { params: LoaderParams }) {
  const { todoId } = params;

  return queryClient.fetchQuery({
    queryKey: ["todo", todoId],
    queryFn: async () => await getApiData(`/api/users/1/todos/${todoId}`).then(response => response.apiData),
  });
}

export default TodoDetail;
