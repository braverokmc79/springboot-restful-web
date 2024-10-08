import React, { createContext,  useEffect,  useState } from "react";

export const AuthContext = createContext({});

export interface AuthContextProps {
  children: React.ReactNode;
}

export interface AuthContextType {
    number: number;
    isAuthenticated: boolean;
    setIsAuthenticated: (isAuthenticated: boolean) => void;
    // other properties...
}

const AuthProvider: React.FC<AuthContextProps> = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
   
  useEffect(() => {
    if(isAuthenticated){       
        console.log("localStorage 로그인 성공: ");
        localStorage.setItem("isAuthenticated", true.toString());
    }else{
        console.log("localStorage 로그아웃 : ");
        localStorage.removeItem("isAuthenticated");
    }
    
  }, [isAuthenticated]);


  return (
    <AuthContext.Provider value={{ isAuthenticated , setIsAuthenticated }}>
      {children}
    </AuthContext.Provider>

  );
};




export default AuthProvider;

