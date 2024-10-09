import ListTodosComponent from '@/components/todo/ListTodosComponent'
import React from 'react'

const TodoListPage:React.FC = () => {
  return (
    <>
        <h1 className='text-3xl font-bold text-center mt-10 mb-10'>Todo List!</h1>
        <ListTodosComponent />
    </>
  )
}

export default TodoListPage