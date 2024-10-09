import { TodoDTO } from '@/dto/TodoDTO';
import React from 'react'


interface TodoDetailProps {
    todo: TodoDTO;
}
const TodoDetailComponent: React.FC<TodoDetailProps> = ({todo}) => {


  return (
    <div className="max-w-xl mx-auto p-6 bg-white shadow-md rounded-lg mt-10">
      <h2 className="text-2xl font-bold mb-4 text-center text-gray-700">Todo 상세 보기</h2>
      <div className="mb-6">
        <h3 className="text-xl font-semibold text-gray-800">{todo.id}</h3>
        <p className="text-gray-600 mt-2">{todo.description}</p>
      </div>
      <div className="mb-4">
        <p>
          <span className="font-semibold text-gray-700">상태: </span>
          <span className={`text-lg font-semibold ${todo.done === true ? 'text-green-500' : 'text-yellow-500'}`}>
            {todo.done}
          </span>
        </p>
        {/* <p className="mt-2">
          <span className="font-semibold text-gray-700">우선순위: </span>
          <span className={`text-lg font-semibold ${todo.priority === 'High' ? 'text-red-500' : 'text-blue-500'}`}>
            {todo.priority}
          </span>
        </p> */}
        <p className="mt-2">
          <span className="font-semibold text-gray-700">마감일: </span>
          <span className="text-gray-800">{todo.targetDate}</span>
        </p>
      </div>
      <div className="flex justify-between mt-8">
        <button className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600">
          수정하기
        </button>
        <button className="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600">
          삭제하기
        </button>
      </div>
    </div>
  )
}

export default TodoDetailComponent;