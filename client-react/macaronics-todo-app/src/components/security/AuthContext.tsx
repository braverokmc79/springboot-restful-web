import React, { createContext,  useState } from "react";

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


  return (
    <AuthContext.Provider value={{ isAuthenticated , setIsAuthenticated }}>
      {children}
    </AuthContext.Provider>

  );
};




export default AuthProvider;

