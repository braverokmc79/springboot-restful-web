import React, { createContext, useContext, useEffect, useState } from "react";

export const AuthContext = createContext({});

export interface AuthContextProps {
  children: React.ReactNode;
}

const AuthProvider: React.FC<AuthContextProps> = ({ children }) => {
  const [number, setNumber] = useState(0);

  useEffect(()=>{
    const interval = setInterval(()=>{
            setNumber((prev)=>prev+1);
    }, 1000);

    return ()=>clearInterval(interval);  // 컴포넌트 언마운트 시 interval 정리
  },[]);

  return (
    <AuthContext.Provider value={{ number, setNumber }}>
      {children}
    </AuthContext.Provider>
  );
};

// useAuth 훅 생성
export const useAuth = () => {
    return useContext(AuthContext);
};


export default AuthProvider;
