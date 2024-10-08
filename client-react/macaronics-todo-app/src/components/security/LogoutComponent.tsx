import React, { useEffect } from 'react'
import { useNavigate } from 'react-router-dom';
import { useAuth } from './UseAuth';
import { AuthContextType } from './AuthContext';


const LogoutComponent:React.FC = () => {
  const  authContext =useAuth() as AuthContextType;
  const navigator =useNavigate();

  useEffect(()=>{
    authContext.setIsAuthenticated(false);
    navigator("/login");
  }, []);
  

  return (
    <div>LogoutComponent</div>
  )
}

export default LogoutComponent