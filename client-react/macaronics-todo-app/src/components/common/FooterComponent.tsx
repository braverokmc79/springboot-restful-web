import React, { useContext } from 'react'
// import { AuthContext } from '../security/AuthContext';
// import { AuthContextType } from './HeaderComponent';

const FooterComponent:React.FC = () => {
  
  // const authContext = useContext(AuthContext) as AuthContextType; 
  // console.log(  " FooterComponent 인증=======>" ,authContext.number);

  return (
    <footer className='fixed bottom-0 w-full bg-gray-800 text-white text-center py-4 mt-20 '>
      하단
    </footer>
  )
}

export default FooterComponent