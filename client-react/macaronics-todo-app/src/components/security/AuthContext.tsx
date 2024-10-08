import React, { createContext, useState } from "react";

export const AuthContext = createContext({});

export interface AuthContextProps {
  children: React.ReactNode;
}

const AuthProvider: React.FC<AuthContextProps> = ({ children }) => {
  const [number, setNumber] = useState(0);

  return (
    <AuthContext.Provider value={{ number, setNumber }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;
