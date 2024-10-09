import React from 'react'
import { Outlet } from "react-router-dom";


const TodoLayout:React.FC = () => {
  return (
    <div>
         <Outlet />
    </div>
  )
}

export default TodoLayout;