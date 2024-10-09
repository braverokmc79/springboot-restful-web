
import WelcomeComponent from '@/components/todo/WelcomeComponent'
import React from 'react'
import { useParams } from 'react-router-dom'


const TodoApp:React.FC = () => {
  const { username } = useParams();
  //console.log(" param  : username  =>",username);

  return (
    <div className='TodoApp'>
       <WelcomeComponent /> 

    </div>
  )
}

export default TodoApp