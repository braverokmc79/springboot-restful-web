import React from 'react'
import { Link } from 'react-router-dom'

const HomePage:React.FC = () => {
  return (
    <div className='flex flex-col justify-center items-center'>
     <h1 className='text-3xl font-bold text-center mt-10'>Home Page</h1>      
        
          <Link to="/todo/list" className='text-3xl font-bold text-center mt-10
            text-blue-500 hover:text-blue-700
          '>
            할일 목록
        </Link>
    </div>
  )
}

export default HomePage